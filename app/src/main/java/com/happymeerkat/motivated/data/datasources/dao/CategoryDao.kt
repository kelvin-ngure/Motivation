package com.happymeerkat.motivated.data.datasources.dao

import androidx.room.Dao
import androidx.room.Query
import com.happymeerkat.motivated.data.models.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAllCategories(): Flow<List<Category>>
}