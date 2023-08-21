package com.happymeerkat.motivated.di

import android.app.Application
import android.content.ContentValues
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.happymeerkat.motivated.data.datasources.localdb.MotivatedDB
import com.happymeerkat.motivated.data.repository.CategoryRepositoryImpl
import com.happymeerkat.motivated.data.repository.QuoteRepositoryImpl
import com.happymeerkat.motivated.domain.repository.CategoryRepository
import com.happymeerkat.motivated.domain.repository.QuoteRepository
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
    fun provideMotivatedDB(app: Application): MotivatedDB {
        return Room.databaseBuilder(
            app,
            MotivatedDB::class.java,
            MotivatedDB.DATABASE_NAME,
        )
            .createFromAsset("database/Quotes.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
//            .addCallback(object : RoomDatabase.Callback() {
//                override fun onCreate(db: SupportSQLiteDatabase) {
//                    super.onCreate(db)
//                    val contentValues = ContentValues()
//                    contentValues.put ("id",1)
//                    contentValues.put ("name","All")
//                    db.insert("Category", OnConflictStrategy.REPLACE, contentValues)
//                }
//            })
            .build()
    }
}