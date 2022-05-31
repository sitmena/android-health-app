package me.sitech.health.data.endpoints

import okhttp3.ResponseBody
import retrofit2.http.GET

interface EndPoints {

    @GET("api/users")
    suspend fun redeem(): ResponseBody
}