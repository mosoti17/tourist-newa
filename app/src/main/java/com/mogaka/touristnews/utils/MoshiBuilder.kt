package com.mogaka.touristnews.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.addAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.Date

object MoshiBuilder {
    fun create(): Moshi = Moshi.Builder()
       // .add(JavaUtilDateAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()
}