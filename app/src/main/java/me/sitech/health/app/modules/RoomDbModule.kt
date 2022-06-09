package me.sitech.health.app.modules

import android.app.Application
import androidx.room.Room
import me.sitech.health.data.room.AppDatabase
import me.sitech.health.data.room.StepDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localDBModule = module {

    fun provideDataBase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "SiHealth")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(dataBase: AppDatabase): StepDao {
        return dataBase.stepsDao()
    }

    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
}

