package me.sitech.health.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import me.sitech.health.app.utils.RequestState
import me.sitech.health.app.utils.resolveError
import me.sitech.health.data.endpoints.EndPoints
import me.sitech.health.data.model.StepEntity
import me.sitech.health.data.room.StepDao
import me.sitech.health.domain.repository.MainRepository
import okhttp3.ResponseBody

class MainRepositoryImpl(private val endPoints: EndPoints, private val db: StepDao): MainRepository {

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

    override suspend fun deleteStepsRecord(stepEntity: StepEntity): Flow<RequestState<Unit>> = flow {
        try {
            db.delete(stepEntity)
            emit(RequestState.Loading(false))
            emit(RequestState.Success(Unit))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(RequestState.Loading(false))
            emit(e.resolveError())
        }
    }.onStart {
        emit(RequestState.Loading(true))
    }

    override suspend fun insertStepsRecord(stepEntity: StepEntity): Flow<RequestState<Unit>> = flow {
        try {
            db.insert(stepEntity)
            emit(RequestState.Loading(false))
            emit(RequestState.Success(Unit))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(RequestState.Loading(false))
            emit(e.resolveError())
        }
    }.onStart {
        emit(RequestState.Loading(true))
    }

    override suspend fun getStepRecords(): Flow<RequestState<List<StepEntity>>> = flow {
        try {
            val data = db.getAll()
            emit(RequestState.Loading(false))
            emit(RequestState.Success(data))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(RequestState.Loading(false))
            emit(e.resolveError())
        }
    }.onStart {
        emit(RequestState.Loading(true))
    }
}