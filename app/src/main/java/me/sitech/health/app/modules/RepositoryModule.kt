package me.sitech.health.app.modules

import me.sitech.health.data.endpoints.EndPoints
import me.sitech.health.data.repository.MainRepositoryImpl
import me.sitech.health.domain.repository.MainRepository
import org.koin.android.ext.koin.androidContext
import android.content.Context
import org.koin.dsl.module

val repositoryModule = module {
    single { provideMainRepository(get()) }
}

fun provideMainRepository(endPoints: EndPoints): MainRepository {
    return MainRepositoryImpl(endPoints)
}