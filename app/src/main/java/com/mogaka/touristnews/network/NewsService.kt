package com.mogaka.touristnews.network

import com.mogaka.touristnews.data.models.ApiResponse
import com.mogaka.touristnews.data.models.News
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("Feed/GetNewsFeed")
    suspend fun getFeeds(@Query("page") page: Int): ApiResponse<News>
}