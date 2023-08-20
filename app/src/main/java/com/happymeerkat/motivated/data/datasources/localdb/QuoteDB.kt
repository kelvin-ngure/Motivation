package com.happymeerkat.motivated.data.datasources.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.happymeerkat.motivated.data.datasources.dao.CategoryDao
import com.happymeerkat.motivated.data.datasources.dao.QuoteDao
import com.happymeerkat.motivated.data.models.Category
import com.happymeerkat.motivated.data.models.Quote

@Database(
    entities = [
        Quote::class,
        Category::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MotivatedDB: RoomDatabase() {
    abstract fun getQuoteDao(): QuoteDao
    abstract fun getCategoryDao(): CategoryDao

    companion object {
        val DATABASE_NAME = "MotivatedDB"
    }
}