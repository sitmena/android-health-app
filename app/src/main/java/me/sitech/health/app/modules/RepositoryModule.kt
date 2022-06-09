package me.sitech.health.app.modules

import me.sitech.health.data.endpoints.EndPoints
import me.sitech.health.data.repository.MainRepositoryImpl
import me.sitech.health.domain.repository.MainRepository
import me.sitech.health.data.room.StepDao
import org.koin.dsl.module

val repositoryModule = module {
    single { provideMainRepository(get(),get()) }
}

fun provideMainRepository(endPoints: EndPoints,dao: StepDao): MainRepository {
    return MainRepositoryImpl(endPoints,dao)
}