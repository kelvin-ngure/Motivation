package com.happymeerkat.motivated.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quote(
    @PrimaryKey
    val id: Int,
    val quote: String,
    val author: String? = null,
    val context: String? = null,
    val categoryId: Int = 1, // 1 is general
    val favorite: Boolean = false
)