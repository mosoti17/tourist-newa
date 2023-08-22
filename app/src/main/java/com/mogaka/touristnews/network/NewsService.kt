package com.mogaka.touristnews.network

import com.mogaka.touristnews.data.ApiResponse
import com.mogaka.touristnews.data.News
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("Feed/GetNewsFeed")
    suspend fun getFeeds(@Query("page") page: Int): ApiResponse<News>
}