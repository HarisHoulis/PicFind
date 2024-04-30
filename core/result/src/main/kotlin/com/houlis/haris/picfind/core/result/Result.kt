package com.houlis.haris.picfind.core.result

import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.Success

inline fun <R, T> Result<T, Throwable>.fold(onSuccess: (value: T) -> R, onFailure: (exception: Throwable) -> R): R =
    when (this) {
        is Success -> onSuccess(value)
        is Failure -> onFailure(reason)
    }
