package com.andreispanait.beers.network.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.PrimaryKey
import com.andreispanait.beers.database.model.Ingredients
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BeerNetwork(
    val id: Int,
    @Json(name = "image_url") val image: String?,
    val name: String,
    val description: String,
    val ingredients: IngredientsNetwork
)