package me.sitech.health.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import me.sitech.health.data.model.StepEntity

@Dao
interface StepDao {

    @Query("SELECT * FROM stepentity")
    suspend fun getAll(): List<StepEntity>

    @Insert
    suspend fun insert(stepEntity: StepEntity)

    @Delete
    suspend fun delete(stepEntity: StepEntity)
}