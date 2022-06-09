package me.sitech.health.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class StepEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "datetime") val dateTime: Long,
    @ColumnInfo(name = "step_count") val stepCount: Int = 0
)
