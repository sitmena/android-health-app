package me.sitech.health.app.app

import android.app.Application
import me.sitech.health.app.modules.*
import org.koin.core.context.startKoin

class HealthApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                apiModule,
                viewModelModule,
                useCaseModule,
                repositoryModule,
                networkModule,
            )
        }
    }

}