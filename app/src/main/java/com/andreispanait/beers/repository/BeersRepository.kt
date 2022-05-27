package com.andreispanait.beers.repository

import android.util.Log
import com.andreispanait.beers.database.dao.BeerDao
import com.andreispanait.beers.database.dao.IngredientsDao
import com.andreispanait.beers.database.model.Beer
import com.andreispanait.beers.database.model.BeerAndIngredients
import com.andreispanait.beers.extensions.toBeer
import com.andreispanait.beers.extensions.toIngredients
import com.andreispanait.beers.network.BeersApiService
import com.andreispanait.beers.network.model.BeerNetwork
import com.andreispanait.beers.utils.OperationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeersRepository @Inject constructor(
    private val beersApiService: BeersApiService,
    private val beerDao: BeerDao,
    private val ingredientsDao: IngredientsDao
) {


    fun getBeersFromDb(): Flow<OperationResult<List<BeerAndIngredients>>> = beerDao.getAllWithIngredients().map {
        if (it.isEmpty()) OperationResult.Loading() else OperationResult.Success(it)
    }.catch {
        emit(OperationResult.Error(it.localizedMessage))
    }

    suspend fun getBeersFromApi(): Boolean = withContext(Dispatchers.IO) {
        val beersNetwork: List<BeerNetwork>?
        try {
            beersNetwork = beersApiService.getAllBeers().body()
            Log.d("BEERS_REPOSITORY", beersNetwork.toString())
        } catch (ex: UnknownHostException) {
            ex.printStackTrace()
            return@withContext false
        }
        try {
            beersNetwork?.map(BeerNetwork::toBeer)?.let { beerDao.insert(it) }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return@withContext false
        }

        try {
            beersNetwork?.map { it.ingredients.toIngredients(it.id) }?.let {
                ingredientsDao.insert(it)
            }

        } catch (ex: Exception) {
            ex.printStackTrace()
            return@withContext false
        }
        return@withContext true

    }
}