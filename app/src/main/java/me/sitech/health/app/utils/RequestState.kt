package me.sitech.health.app.utils

sealed class RequestState<out T> {
    data class Loading(var isLoading: Boolean) : RequestState<Nothing>()
    data class Error(var exception: Throwable) : RequestState<Nothing>()
    data class Success<T>(var data: T) : RequestState<T>()
}