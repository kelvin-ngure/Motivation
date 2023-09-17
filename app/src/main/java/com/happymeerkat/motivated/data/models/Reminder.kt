package com.happymeerkat.motivated.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class Reminder(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val time: Long,
)