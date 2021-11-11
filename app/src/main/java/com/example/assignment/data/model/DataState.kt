package com.example.assignment.data.model

sealed class DataState<out T> {
    data class Success<out T>(val baseResponseData: T) : DataState<T>()
    data class GenericError(
        val errorMessage: String? = null,
        val errorResponse: ErrorResponse? = null
    ) : DataState<Nothing>()

    data class NetworkError(val exception: Exception, val errorMessage: String) :
        DataState<Nothing>()

    object Loading : DataState<Nothing>()
}