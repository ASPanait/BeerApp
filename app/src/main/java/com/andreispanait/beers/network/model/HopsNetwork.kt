package com.andreispanait.beers.network.model

import android.os.Parcelable
import androidx.room.Entity
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
data class HopsNetwork(
    val name: String
)