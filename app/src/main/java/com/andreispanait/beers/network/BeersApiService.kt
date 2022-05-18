package com.andreispanait.beers.network

import com.andreispanait.beers.database.model.Beer
import com.andreispanait.beers.network.model.BeerNetwork
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BeersApiService {
    @GET("beers")
    suspend fun getAllBeers(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 50
    ): Response<List<BeerNetwork>>
}