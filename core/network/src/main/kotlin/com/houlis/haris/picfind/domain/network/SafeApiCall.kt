package com.houlis.haris.picfind.domain.network

import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.Success
import retrofit2.HttpException
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

/** Wraps a retrofit call and returns an [Result] with the response or error code. */
suspend fun <T> safeApiCall(block: suspend () -> retrofit2.Response<T>): Result<T, Exception> = try {
    val response = block()
    if (response.isSuccessful) {
        val body = response.body()
        if (body != null) {
            Success(body)
        } else {
            Failure(IOException("Empty body"))
        }
    } else {
        Failure(IOException("Failed to read response, error code [${response.code()}]"))
    }
} catch (ex: CancellationException) {
    throw ex
} catch (ex: HttpException) {
    Failure(ex)
} catch (ex: IOException) {
    Failure(ex)
}
