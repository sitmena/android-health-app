package me.sitech.health.app.modules

import me.sitech.health.data.endpoints.EndPoints
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideApi(retrofit: Retrofit): EndPoints {
        return retrofit.create(EndPoints::class.java)
    }
    single { provideApi(get()) }

}