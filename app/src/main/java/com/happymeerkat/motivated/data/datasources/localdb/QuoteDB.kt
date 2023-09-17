package com.happymeerkat.motivated.data.datasources.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.happymeerkat.motivated.data.datasources.dao.CategoryDao
import com.happymeerkat.motivated.data.datasources.dao.FavoriteDao
import com.happymeerkat.motivated.data.datasources.dao.QuoteDao
import com.happymeerkat.motivated.data.datasources.dao.ReminderDao
import com.happymeerkat.motivated.data.models.Category
import com.happymeerkat.motivated.data.models.Favorite
import com.happymeerkat.motivated.data.models.Quote
import com.happymeerkat.motivated.data.models.Reminder

@Database(
    entities = [
        Quote::class,
        Category::class,
        Favorite::class,
        Reminder::class
    ],
    version = 6,
    exportSchema = false
)
abstract class MotivatedDB: RoomDatabase() {
    abstract fun getQuoteDao(): QuoteDao
    abstract fun getCategoryDao(): CategoryDao

    abstract fun getFavoriteDao(): FavoriteDao

    abstract fun getReminderDao(): ReminderDao


    companion object {
        val DATABASE_NAME = "MotivatedDB"
    }
}