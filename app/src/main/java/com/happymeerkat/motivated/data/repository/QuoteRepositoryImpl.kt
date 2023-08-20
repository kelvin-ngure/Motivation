package com.happymeerkat.motivated.data.repository

import com.happymeerkat.motivated.data.datasources.dao.QuoteDao
import com.happymeerkat.motivated.data.models.Quote
import com.happymeerkat.motivated.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val dao: QuoteDao
): QuoteRepository {
    override fun getAllQuotes(): Flow<List<Quote>> {
        TODO("Not yet implemented")
    }
}