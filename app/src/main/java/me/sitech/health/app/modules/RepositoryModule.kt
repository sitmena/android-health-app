package me.sitech.health.app.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.sitech.health.data.endpoints.EndPoints
import me.sitech.health.data.repository.MainRepositoryImpl
import me.sitech.health.domain.repository.MainRepository

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideMainRepository(endPoints: EndPoints): MainRepository =
        MainRepositoryImpl(endPoints)
}