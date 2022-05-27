package com.andreispanait.beers.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.andreispanait.beers.database.model.Ingredients

@Dao
interface IngredientsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ingredients: List<Ingredients>)
}