package com.happymeerkat.motivated.data.repository

import com.happymeerkat.motivated.data.datasources.dao.FavoriteDao
import com.happymeerkat.motivated.data.models.Favorite
import com.happymeerkat.motivated.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
): FavoriteRepository {
    override fun getAllFavorites(): Flow<List<Favorite>> {
        return favoriteDao.getAllFavorites()
    }

    override suspend fun insertFavorite(favorite: Favorite) {
        favoriteDao.insertFavorite(favorite)
    }

    override suspend fun deleteFavorite(favorite: Favorite) {
        favoriteDao.deleteFavorite(favorite)
    }
}