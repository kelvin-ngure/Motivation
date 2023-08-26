package com.happymeerkat.motivated.domain.repository

import com.happymeerkat.motivated.data.models.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getAllFavorites(): Flow<List<Favorite>>

    suspend fun insertFavorite(favorite: Favorite)

    suspend fun deleteFavorite(favorite: Favorite)
}