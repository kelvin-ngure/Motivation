package com.happymeerkat.motivated.data.datasources.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.happymeerkat.motivated.data.models.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM Favorite")
    fun getAllFavorites(): Flow<List<Favorite>>

    @Upsert
    suspend fun insertFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)
}