package com.happymeerkat.motivated.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey
    val id: Int,
    val name: Int
)
