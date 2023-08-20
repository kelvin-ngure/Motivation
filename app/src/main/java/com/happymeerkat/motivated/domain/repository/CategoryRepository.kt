package com.happymeerkat.motivated.domain.repository

import com.happymeerkat.motivated.data.models.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getAllCategories(): Flow<List<Category>>
}