package com.houlis.haris.picfind.ui.common.mvi

import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val BUFFER_SIZE = 64

/**
 * Responsible for providing a list of [Middleware]s
 *
 * @param S - The [State] managed by this [ViewModel]
 * @param A - the [Action]s this [ViewModel] handles
 */
fun interface MwProvider<S : State, A : Action> {
    operator fun invoke(dispatcher: Dispatcher<A>): List<Middleware<S, A>>
}

/**
 * Base class for all [ViewModel]s adhering to the MVI framework
 *
 * @param S - The [State] managed by this [ViewModel]
 * @param A - the [Action]s this [ViewModel] handles
 * @param coroutineScope - a [CloseableCoroutineScope] to launch coroutines on
 * @param reducer - the [Reducer] that generates new state from the current [State] and [Action]s
 * @param middlewaresProvider - provides a list of [Middleware]s to handle [Action]s
 * @param initialState - the initial [State]
 */
open class MviViewModel<S : State, A : Action>(
    private val coroutineScope: CoroutineScope,
    private val reducer: Reducer<S, A>,
    private val middlewaresProvider: MwProvider<S, A> = MwProvider { emptyList() },
    initialState: S,
) : ViewModel(viewModelScope = coroutineScope), Dispatcher<A> {

    private val middlewares: List<Middleware<S, A>>
        get() = middlewaresProvider(this)

    private val actions = MutableSharedFlow<A>(extraBufferCapacity = BUFFER_SIZE)

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)

    /** The [State] managed by this [MviViewModel] */
    var state: StateFlow<S> = _state.asStateFlow()

    init {
        coroutineScope.launch {
            actions
                .onEach { action ->
                    middlewares.fastForEach { middleware ->
                        middleware.process(state.value, action)
                    }
                }
                .collect()
        }
        coroutineScope.launch {
            actions.collect { action ->
                _state.update { currentState ->
                    reducer.reduce(currentState, action)
                }
            }
        }
    }

    override fun dispatch(action: A) {
        coroutineScope.launch {
            actions.emit(action)
        }
    }
}
