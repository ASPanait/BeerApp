package com.andreispanait.beers.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Beer(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @Json(name = "image_url") val image: String?,
    val name: String
): Parcelable
