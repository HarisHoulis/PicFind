package com.houlis.haris.picfind.domain.dispatcher

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

internal object DispatcherProviderInstance : DispatcherProvider {
    override val default: CoroutineContext
        get() = Dispatchers.Default

    override val io: CoroutineContext
        get() = Dispatchers.IO

    override val main: CoroutineContext
        get() = Dispatchers.Main

    override val mainImmediate: CoroutineContext
        get() = Dispatchers.Main.immediate

    override val unconfined: CoroutineContext
        get() = Dispatchers.Unconfined
}
