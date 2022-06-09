package me.sitech.health.domain.repository

import kotlinx.coroutines.flow.Flow
import me.sitech.health.app.utils.RequestState
import me.sitech.health.data.model.StepEntity
import okhttp3.ResponseBody

interface MainRepository {

    suspend fun redeem(): Flow<RequestState<ResponseBody>>

    suspend fun insertStepsRecord(stepEntity: StepEntity): Flow<RequestState<Unit>>
    suspend fun deleteStepsRecord(stepEntity: StepEntity): Flow<RequestState<Unit>>
    suspend fun getStepRecords(): Flow<RequestState<List<StepEntity>>>

}