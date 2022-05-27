package com.andreispanait.beers.database.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Parcelize
data class BeerAndIngredients(
    @Embedded val beer: Beer,
    @Relation(
        parentColumn = "id",
        entityColumn = "beerId"
    )
    val ingredients: Ingredients

): Parcelable
