package com.andreispanait.beers.utils

sealed class OperationResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(
        result: T? = null
    ) : OperationResult<T>(result)

    class Error<T>(
        message: String? = null
    ) : OperationResult<T>(message = message)

    class Loading<T> : OperationResult<T>()
}
