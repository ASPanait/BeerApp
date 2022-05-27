package com.andreispanait.beers.database.dao

import androidx.room.*
import com.andreispanait.beers.database.model.Beer
import com.andreispanait.beers.database.model.BeerAndIngredients
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(beers: List<Beer>)

    @Query("SELECT * FROM Beer ORDER BY name")
    fun getAll(): Flow<List<Beer>>

    @Transaction
    @Query("SELECT * FROM Beer ORDER BY name")
    fun getAllWithIngredients(): Flow<List<BeerAndIngredients>>

}