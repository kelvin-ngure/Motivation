package com.happymeerkat.motivated.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite (
    @PrimaryKey
    val id: Int, // just the quote id for now
)