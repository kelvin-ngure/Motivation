package com.happymeerkat.motivated.data.models

import androidx.room.Entity

@Entity
data class Quote(
    val id: Int,
    val quote: String,
    val author: String,
    val context: String,
    val categoryId: Int
)