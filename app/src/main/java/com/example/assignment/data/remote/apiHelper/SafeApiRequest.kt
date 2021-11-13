package com.example.assignment.data.remote.apiHelper

import com.android.wakeMate.data.remote.NetworkHelper
import com.example.assignment.data.local.LocalDao
import com.example.assignment.data.model.DataState
import com.example.assignment.data.model.ErrorResponse
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}

open class SafeApiRequest
@Inject constructor(
    private val networkHelper: NetworkHelper,
    private val localDao: LocalDao
) {
    suspend fun <T : Any> apiRequest(dataRequest: suspend () -> T): DataState<T> {
        return try {
            if (networkHelper.isNetworkConnected()) {
                DataState.Success(dataRequest.invoke())
            } else
                throw NoInternetException("Please check your Internet Connection")
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> DataState.GenericError(
                    throwable.message,
                    null
                )
                is HttpException -> {
                    val errorResponse = convertErrorBody(throwable)
                    DataState.GenericError(errorMessage = errorResponse?.message, errorResponse)
                }
                is SocketTimeoutException -> DataState.NetworkError(
                    throwable,
                    getErrorMessage(ErrorCodes.SocketTimeOut.code)
                )
                is NoInternetException -> DataState.NetworkError(
                    throwable,
                    throwable.message!!
                )

                else -> {
                    DataState.GenericError(throwable.message, null)
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
            moshiAdapter.fromJson(it)
        }
    } catch (exception: Exception) {
        null
    }
}

private fun getErrorMessage(code: Int): String {
    return when (code) {
        ErrorCodes.SocketTimeOut.code -> "Timeout"
        400 -> "Bad Request"
        401 -> "Unauthorised"
        403 -> "Forbidden"
        404 -> "Not found"
        409 -> "Conflict"
        500 -> "Internal Server Error"
        else -> "Something went wrong"
    }
}


