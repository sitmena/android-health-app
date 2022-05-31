package me.sitech.health.app.modules

import android.content.Context
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.sitech.health.app.app.HealthApp
import me.sitech.health.data.endpoints.EndPoints
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): HealthApp {
        return app as HealthApp
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {

        return Retrofit.Builder().baseUrl("https://reqres.in/").client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        val okHttpClientBuilder = OkHttpClient().newBuilder()

        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(logger)

        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideContext(application: HealthApp): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): EndPoints {
        return retrofit.create(EndPoints::class.java)
    }
}