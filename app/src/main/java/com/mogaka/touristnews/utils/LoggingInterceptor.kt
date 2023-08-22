package com.mogaka.touristnews.utils


import okhttp3.logging.HttpLoggingInterceptor

object LoggingInterceptor {

    /**
     * Creates and configures an HttpLoggingInterceptor.
     * @return An instance of HttpLoggingInterceptor.
     */
    fun create(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}