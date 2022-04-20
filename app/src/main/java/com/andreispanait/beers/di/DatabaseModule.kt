package com.andreispanait.beers.di

import android.content.Context
import androidx.room.Database
import androidx.room.ProvidedAutoMigrationSpec
import androidx.room.Room
import com.andreispanait.beers.database.BeersDatabase
import com.andreispanait.beers.database.dao.BeerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): BeersDatabase {
        val database = Room.databaseBuilder(
            context,
            BeersDatabase::class.java,
            "beers_database"
        ).fallbackToDestructiveMigration().build()
        return database
    }

    @Provides
    fun provideBeerDao(database: BeersDatabase): BeerDao = database.beerDao()

}