package com.happymeerkat.motivated.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.happymeerkat.motivated.data.datasources.localdb.MotivatedDB
import com.happymeerkat.motivated.data.preferences.ThemePreferencesRepository
import com.happymeerkat.motivated.data.repository.CategoryRepositoryImpl
import com.happymeerkat.motivated.data.repository.FavoriteRepositoryImpl
import com.happymeerkat.motivated.data.repository.QuoteRepositoryImpl
import com.happymeerkat.motivated.data.repository.ReminderRepositoryImpl
import com.happymeerkat.motivated.domain.repository.CategoryRepository
import com.happymeerkat.motivated.domain.repository.FavoriteRepository
import com.happymeerkat.motivated.domain.repository.QuoteRepository
import com.happymeerkat.motivated.domain.repository.ReminderRepository
import com.happymeerkat.motivated.domain.themes.ThemeManager
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
    fun provideThemeManager(themePreferencesRepository: ThemePreferencesRepository): ThemeManager {
        return ThemeManager(themePreferencesRepository)
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
    fun provideReminderRepository(db: MotivatedDB): ReminderRepository {
        return ReminderRepositoryImpl(db.getReminderDao())
    }


    @Provides
    @Singleton
    fun provideMotivatedDB(app: Application): MotivatedDB {
        return Room.databaseBuilder(
            app,
            MotivatedDB::class.java,
            MotivatedDB.DATABASE_NAME,
        )
            .createFromAsset("database/MotivationApp.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}