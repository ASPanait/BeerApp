package com.andreispanait.beers.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.andreispanait.beers.BuildConfig
import com.andreispanait.beers.database.dao.BeerDao
import com.andreispanait.beers.database.dao.IngredientsDao
import com.andreispanait.beers.database.model.Beer
import com.andreispanait.beers.database.model.Hops
import com.andreispanait.beers.database.model.Ingredients
import com.andreispanait.beers.database.model.Malt
import com.andreispanait.beers.database.type_converters.HopsConverter
import com.andreispanait.beers.database.type_converters.MaltConverter

@Database(entities = [Beer::class, Ingredients::class], version = BuildConfig.VERSION_CODE)
@TypeConverters(MaltConverter::class, HopsConverter::class)
abstract class BeersDatabase: RoomDatabase() {


    abstract fun beerDao(): BeerDao

    abstract fun ingredientsDao(): IngredientsDao

}