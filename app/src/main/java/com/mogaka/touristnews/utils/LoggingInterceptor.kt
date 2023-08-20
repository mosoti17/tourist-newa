package com.mogaka.touristnews.utils

import okhttp3.logging.HttpLoggingInterceptor

object LoggingInterceptor {

    /**
     * Creates and configures an HttpLoggingInterceptor instance based on the build configuration.
     * @return An instance of HttpLoggingInterceptor.
     */
    fun create(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY //if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC else HttpLoggingInterceptor.Level.NONE
    }
}