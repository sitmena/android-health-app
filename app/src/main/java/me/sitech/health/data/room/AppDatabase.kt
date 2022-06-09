package me.sitech.health.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import me.sitech.health.data.model.StepEntity

@Database(entities = [StepEntity::class], version = 2)
abstract class AppDatabase: RoomDatabase() {

    abstract fun stepsDao(): StepDao

}