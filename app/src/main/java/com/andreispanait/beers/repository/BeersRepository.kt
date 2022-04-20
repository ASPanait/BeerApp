package com.andreispanait.beers.repository

import com.andreispanait.beers.database.dao.BeerDao
import com.andreispanait.beers.database.model.Beer
import com.andreispanait.beers.network.BeersApiService
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
    private val beerDao: BeerDao
) {


    fun getBeersFromDb(): Flow<OperationResult<List<Beer>>> = beerDao.getAll().map {
        if (it.isEmpty()) OperationResult.Loading() else OperationResult.Success(it)
    }.catch {
        emit(OperationResult.Error(it.localizedMessage))
    }

    suspend fun getBeersFromApi(): Boolean = withContext(Dispatchers.IO) {
        val beers: List<Beer>?
        try {
            beers = beersApiService.getAllBeers().body()
            delay(10000)
        } catch (ex: UnknownHostException) {
            ex.printStackTrace()
            return@withContext false
        }
        try {
            beers?.let {
                beerDao.insert(it)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return@withContext false
        }
        return@withContext true

    }
}