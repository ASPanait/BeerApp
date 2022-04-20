package com.andreispanait.beers.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andreispanait.beers.database.model.Beer
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(beers: List<Beer>)

    @Query("SELECT * FROM Beer ORDER BY name")
    fun getAll(): Flow<List<Beer>>

}