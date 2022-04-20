package com.andreispanait.beers.ui.beers

import com.andreispanait.beers.database.model.Beer

interface BeersListener {
    fun beerClick(beer: Beer)
}