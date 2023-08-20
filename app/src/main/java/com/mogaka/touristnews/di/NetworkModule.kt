package com.mogaka.touristnews.di

import com.mogaka.touristnews.network.NewsService
import com.mogaka.touristnews.network.TouristService
import com.mogaka.touristnews.utils.BASE_URL
import com.mogaka.touristnews.utils.HttpClient
import com.mogaka.touristnews.utils.MoshiBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesNewsService(retrofit: Retrofit): NewsService =
        retrofit.create(NewsService::class.java)

    @Singleton
    @Provides
    fun providesTouristService(retrofit: Retrofit): TouristService =
        retrofit.create(TouristService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(HttpClient.create())
        .addConverterFactory(MoshiConverterFactory.create(MoshiBuilder.create()))
        .build()
}