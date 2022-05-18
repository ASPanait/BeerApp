package com.andreispanait.beers.extensions

import com.andreispanait.beers.database.model.Beer
import com.andreispanait.beers.database.model.Hops
import com.andreispanait.beers.database.model.Ingredients
import com.andreispanait.beers.database.model.Malt
import com.andreispanait.beers.network.model.BeerNetwork

fun BeerNetwork.toBeer(): Beer =
    Beer(
        this.id,
        this.image,
        this.name,
        this.description
    )


