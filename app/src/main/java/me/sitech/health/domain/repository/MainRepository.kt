package me.sitech.health.domain.repository

import kotlinx.coroutines.flow.Flow
import me.sitech.health.app.utils.RequestState
import okhttp3.ResponseBody

interface MainRepository {

    suspend fun redeem(): Flow<RequestState<ResponseBody>>

}