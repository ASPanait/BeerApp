package com.andreispanait.beers.ui.beers

import com.andreispanait.beers.database.model.Beer
import com.andreispanait.beers.database.model.BeerAndIngredients

interface BeersListener {
    fun beerClick(beerAndIngredients: BeerAndIngredients)
}