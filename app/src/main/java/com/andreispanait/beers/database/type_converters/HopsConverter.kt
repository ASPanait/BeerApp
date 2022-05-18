package com.andreispanait.beers.database.type_converters

import androidx.room.TypeConverter
import com.andreispanait.beers.database.model.Hops
import com.andreispanait.beers.database.model.Malt
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class HopsConverter {

    @TypeConverter
    fun fromString(value: String?): List<Hops>? {
        return if (value.isNullOrEmpty()) null
        else {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val type = Types.newParameterizedType(List::class.java, Hops::class.java)
            val adapter = moshi.adapter<List<Hops>>(type)
            adapter.fromJson(value)
        }
    }

    @TypeConverter
    fun fromList(value: List<Hops>?): String? {
        return if(value.isNullOrEmpty()) null
        else {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val type = Types.newParameterizedType(List::class.java, Hops::class.java)
            val adapter = moshi.adapter<List<Hops>>(type)
            adapter.toJson(value)
        }

    }
}