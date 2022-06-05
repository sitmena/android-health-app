package me.sitech.health.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import me.sitech.health.app.utils.RequestState
import me.sitech.health.app.utils.resolveError
import me.sitech.health.data.endpoints.EndPoints
import me.sitech.health.domain.repository.MainRepository
import okhttp3.ResponseBody

class MainRepositoryImpl constructor(private val endPoints: EndPoints): MainRepository {

    override suspend fun redeem(): Flow<RequestState<ResponseBody>> = flow {
        try {
            val response = endPoints.redeem()
            emit(RequestState.Loading(false))
            emit(RequestState.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(RequestState.Loading(false))
            emit(e.resolveError())
        }
    }.onStart {
        emit(RequestState.Loading(true))
    }
}