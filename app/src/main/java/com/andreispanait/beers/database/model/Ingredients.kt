package com.andreispanait.beers.database.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class Ingredients(
    @PrimaryKey val beerId: Int,
    val malt: List<Malt>,
    val hops: List<Hops>,
    val yeast: String,
) : Parcelable