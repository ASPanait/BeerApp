package com.andreispanait.beers.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andreispanait.beers.BuildConfig
import com.andreispanait.beers.database.dao.BeerDao
import com.andreispanait.beers.database.model.Beer

@Database(entities = [Beer::class], version = BuildConfig.VERSION_CODE)
abstract class BeersDatabase: RoomDatabase() {


    abstract fun beerDao(): BeerDao

}