package com.andreispanait.beers.extensions

import com.andreispanait.beers.database.model.Hops
import com.andreispanait.beers.database.model.Ingredients
import com.andreispanait.beers.database.model.Malt
import com.andreispanait.beers.network.model.IngredientsNetwork

fun IngredientsNetwork.toIngredients(beerId: Int): Ingredients = Ingredients(
    beerId,
    this.malt.map { Malt(it.name) },
    this.hops.map { Hops(it.name) },
    this.yeast
)