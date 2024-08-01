package com.example.data.network.remote

import com.example.data.network.utils.ConnectionUtils
import com.example.domain.entity.Either
import com.example.domain.entity.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException

class NetworkHandler @Inject constructor(
    val networkUtils: ConnectionUtils,
    ) {

    suspend inline fun <T> callService(
        crossinline retrofitCall: suspend () -> Response<T>
    ): Either<Failure, T> {
        return when (networkUtils.isNetworkAvailable()) {
            true -> {
                try {
                    withContext(Dispatchers.IO) {
                        val response = retrofitCall.invoke()
                        if (response.isSuccessful) {
                            response.body()?.let {
                                return@withContext Either.Right(it)
                            } ?: return@withContext Either.Left(
                                getErrorMessageFromServer(
                                    response.errorBody()?.toString()
                                )
                            )
                        } else return@withContext Either.Left(
                            getErrorMessageFromServer(
                                response.errorBody()?.toString()
                            )
                        )
                    }
                } catch (e: Exception) {
                    Either.Left(parseException(e))
                }
            }
            false -> Either.Left(Failure.NoNetworkDetected)
        }
    }

    suspend fun getErrorMessageFromServer(errorBody: String?): Failure {
        return if (errorBody != null) {
            return withContext(Dispatchers.IO) {
                val serverErrorJson = JSONObject(errorBody)
                when {
                    isServerErrorValid(serverErrorJson.toString()) -> {
                        val code = serverErrorJson[KEY_CODE].toString().toInt()
                        if (code == 401 || code == 403) {
                            return@withContext Failure.UnauthorizedOrForbidden
                        } else {
                            return@withContext Failure.ServerBodyError(
                                code,
                                serverErrorJson[KEY_MESSAGE].toString()
                            )
                        }
                    }
                    serverErrorJson.toString().contains("\"$KEY_MESSAGE\"") -> {
                        return@withContext Failure.ServiceUncaughtFailure(serverErrorJson[KEY_MESSAGE].toString())
                    }
                    else -> return@withContext Failure.None
                }
            }
        } else {
            Failure.None
        }
    }

    private fun isServerErrorValid(error: String): Boolean {
        return error.contains("\"$KEY_CODE\"") && error.contains("\"$KEY_MESSAGE\"")
    }

    fun parseException(throwable: Throwable): Failure {
        return when (throwable) {
            is SocketTimeoutException -> Failure.TimeOut
            is SSLException -> Failure.NetworkConnectionLostSuddenly
            is SSLHandshakeException -> Failure.SSLError
            else -> Failure.None
        }
    }

    companion object {
        private const val KEY_CODE = "status" //code
        private const val KEY_MESSAGE = "message"
    }
}
