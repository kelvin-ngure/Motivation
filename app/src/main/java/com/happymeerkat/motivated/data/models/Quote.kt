package com.happymeerkat.motivated.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Quote(
    @PrimaryKey
    val id: Int,
    val quote: String,
    val author: String? = null,
): Serializable