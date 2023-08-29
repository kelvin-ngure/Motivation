package com.happymeerkat.motivated.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.happymeerkat.motivated.data.datasources.localdb.MotivatedDB
import com.happymeerkat.motivated.data.preferences.BackgroundPreferencesRepository
import com.happymeerkat.motivated.data.preferences.FontPreferencesRepository
import com.happymeerkat.motivated.data.repository.CategoryRepositoryImpl
import com.happymeerkat.motivated.data.repository.FavoriteRepositoryImpl
import com.happymeerkat.motivated.data.repository.QuoteRepositoryImpl
import com.happymeerkat.motivated.domain.repository.CategoryRepository
import com.happymeerkat.motivated.domain.repository.FavoriteRepository
import com.happymeerkat.motivated.domain.repository.QuoteRepository
import com.happymeerkat.motivated.domain.themes.BackgroundManager
import com.happymeerkat.motivated.domain.themes.FontManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "com.happymeerkat.preferences"
)

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext applicationContext: Context): DataStore<Preferences> {
        return applicationContext.userDataStore
    }


    @Provides
    @Singleton
    fun provideFontManager(fontPreferencesRepository: FontPreferencesRepository): FontManager {
        return FontManager(fontPreferencesRepository)
    }

    @Provides
    @Singleton
    fun provideBackgroundManager(backgroundPreferencesRepository: BackgroundPreferencesRepository): BackgroundManager {
        return BackgroundManager(backgroundPreferencesRepository)
    }


    @Provides
    @Singleton
    fun provideQuoteRepository(db: MotivatedDB): QuoteRepository {
        return QuoteRepositoryImpl(db.getQuoteDao())
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(db: MotivatedDB): CategoryRepository {
        return CategoryRepositoryImpl(db.getCategoryDao())
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(db: MotivatedDB): FavoriteRepository {
        return FavoriteRepositoryImpl(db.getFavoriteDao())
    }


    @Provides
    @Singleton
    fun provideMotivatedDB(app: Application): MotivatedDB {
        return Room.databaseBuilder(
            app,
            MotivatedDB::class.java,
            MotivatedDB.DATABASE_NAME,
        )
            .createFromAsset("database/Quotes.db")
            .allowMainThreadQueries()
            .build()
    }
}