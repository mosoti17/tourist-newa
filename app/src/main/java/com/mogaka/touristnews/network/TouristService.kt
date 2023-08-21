package com.mogaka.touristnews.network

import com.mogaka.touristnews.data.ApiResponse
import com.mogaka.touristnews.data.Tourist
import retrofit2.http.GET
import retrofit2.http.Query

interface TouristService {

    @GET("Tourist")
    suspend fun getTourists(@Query("page") page: Int): ApiResponse<Tourist>

    @GET("Tourist/{id}")
    suspend fun getTourist(): List<Tourist>
}