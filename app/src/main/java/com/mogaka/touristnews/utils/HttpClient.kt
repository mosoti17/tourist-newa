package com.mogaka.touristnews.utils

import android.content.Context
import okhttp3.Cache
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import java.io.File

object HttpClient {

    /**
     * Creates and configures an OkHttpClient instance with the necessary interceptors and timeouts.
     * @return An instance of OkHttpClient.
     */
    fun create(): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor.create())
            .connectTimeout(API_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(API_READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }
}