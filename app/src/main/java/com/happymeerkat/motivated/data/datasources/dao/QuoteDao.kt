package com.happymeerkat.motivated.data.datasources.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.happymeerkat.motivated.data.models.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    @Query("SELECT * FROM Quote")
    fun getAllQuotes(): Flow<List<Quote>>

    @Upsert
    suspend fun upsertQuote(item: Quote)

    @Delete
    suspend fun deleteQuote(item: Quote)
}