package com.mogaka.touristnews.network

import com.mogaka.touristnews.data.Tourist
import retrofit2.http.GET

interface TouristService {

    @GET("Tourist")
    suspend fun getTourists(): List<Tourist>

    @GET("Tourist/{id}")
    suspend fun getTourist(): List<Tourist>
}