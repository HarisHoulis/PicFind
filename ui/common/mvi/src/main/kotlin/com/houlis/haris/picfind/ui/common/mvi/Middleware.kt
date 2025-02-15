package com.houlis.haris.picfind.ui.common.mvi

/**
 * Handles asynchronous [Action] processing in the MVI framework
 *
 * @param dispatcher The [Dispatcher] to dispatch [Action]s to
 */
abstract class Middleware<S : State, A : Action>(private val dispatcher: Dispatcher<A>) {

    /**
     * Processes the [Action].
     *
     * @param state the current [State]
     * @param action the [Action] to process
     */
    abstract suspend fun process(state: S, action: A)

    protected fun dispatch(action: A) = dispatcher.dispatch(action)
}
