package com.happymeerkat.motivated.domain.repository

import com.happymeerkat.motivated.data.models.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    fun getAllQuotes(): Flow<List<Quote>>
    suspend fun upsertQuote(quote: Quote)
}