package me.sitech.health.app.app

import android.app.Application
import me.sitech.health.app.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HealthApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@HealthApp)
            modules(
                apiModule,
                viewModelModule,
                useCaseModule,
                localDBModule,
                repositoryModule,
                networkModule,
            )
        }
    }

}