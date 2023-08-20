package com.happymeerkat.motivated.data.repository

import com.happymeerkat.motivated.data.datasources.dao.CategoryDao
import com.happymeerkat.motivated.data.models.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
): CategoryDao {
    override fun getAllCategories(): Flow<List<Category>> {
        TODO("Not yet implemented")
    }
}