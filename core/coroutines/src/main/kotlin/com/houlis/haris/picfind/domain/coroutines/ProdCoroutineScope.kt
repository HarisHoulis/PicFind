package com.houlis.haris.picfind.domain.coroutines

import com.houlis.haris.picfind.domain.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class ProdCoroutineScope @Inject constructor(dispatcherProvider: DispatcherProvider) : CoroutineScope {
    /** @{inheritDoc} */
    override val coroutineContext: CoroutineContext = SupervisorJob() + dispatcherProvider.mainImmediate
}
