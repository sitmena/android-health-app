package me.sitech.health.app.utils

sealed class RequestState<out T> {
    object Loading : RequestState<Nothing>()
    data class Error(var exception: Throwable) : RequestState<Nothing>()
    data class Success<T>(var data: T) : RequestState<T>()
}