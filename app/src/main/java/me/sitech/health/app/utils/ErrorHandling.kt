package me.sitech.health.app.utils

import org.json.JSONObject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


fun Exception.resolveError(): RequestState.Error {
    var error = this

    when (error) {
        is SocketTimeoutException -> {
            error = NetworkErrorException(errorMessage = "connection error!")
        }
        is ConnectException -> {
            error = NetworkErrorException(errorMessage = "no internet access!")
        }
        is UnknownHostException -> {
            error = NetworkErrorException(errorMessage = "no internet access!")
        }
    }

    if(error is HttpException){
        when(error.code()){
            500 -> {
                error = NetworkErrorException(error.code(),  "internal error!")
            }
            401 -> {
                error = AuthenticationException("authentication error!")
            }
            400,402,403 -> {
                error = NetworkErrorException.parseException(error)
            }

        }
    }
    return RequestState.Error(error)
}

open class NetworkErrorException(
    val errorCode: Int = -1,
    val errorMessage: String,
    val response: String = ""
) : Exception() {
    override val message: String
        get() = localizedMessage

    override fun getLocalizedMessage(): String {
        return errorMessage
    }

    companion object {
        fun parseException(e: HttpException): NetworkErrorException {
            val errorBody = e.response()?.errorBody()?.string()

            return try {//here you can parse the error body that comes from server
                NetworkErrorException(e.code(), JSONObject(errorBody!!).getString("message"))
            } catch (_: Exception) {
                NetworkErrorException(e.code(), "unexpected error!!ً")
            }
        }
    }
}

class AuthenticationException(authMessage: String) :
    NetworkErrorException(errorMessage = authMessage)