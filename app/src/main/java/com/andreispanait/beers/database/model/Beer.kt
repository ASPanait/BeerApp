package com.andreispanait.beers.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class Beer(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val image: String?,
    val name: String,
    val description: String
) : Parcelable
