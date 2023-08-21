package com.mogaka.touristnews.network

import com.mogaka.touristnews.data.models.ApiResponse
import com.mogaka.touristnews.data.models.Tourist
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TouristService {

    @GET("Tourist")
    suspend fun getTourists(@Query("page") page: Int): ApiResponse<Tourist>

}