package com.andreispanait.beers.database.model

import android.os.Parcelable
import androidx.room.Entity
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@Parcelize

data class Hops(
    val name: String

) : Parcelable