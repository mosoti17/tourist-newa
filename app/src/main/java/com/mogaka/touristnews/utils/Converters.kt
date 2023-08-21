package com.mogaka.touristnews.utils

import androidx.room.TypeConverter
import com.mogaka.touristnews.data.models.MultiMedia
import com.mogaka.touristnews.data.models.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class Converters {
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @TypeConverter
    fun fromUser(value: String): User? {

        val adapter = moshi.adapter(User::class.java)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun toUser(user: User?): String {

        val adapter = moshi.adapter(User::class.java)
        return adapter.toJson(user)
    }

    @TypeConverter
    fun fromMultimedia(value: String): List<MultiMedia>? {
        try {
        val type = Types.newParameterizedType(List::class.java, MultiMedia::class.java)
        val adapter = moshi.adapter<List<MultiMedia>>(type)
        return adapter.fromJson(value)
        }catch(e:Exception){
           return listOf()
        }
    }

    @TypeConverter
    fun fromMultimedia(multiMedia: List<MultiMedia>): String {

        val adapter = moshi.adapter(List::class.java)
        return adapter.toJson(multiMedia)
    }
}