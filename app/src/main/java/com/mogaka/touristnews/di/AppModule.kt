package com.mogaka.touristnews.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.mogaka.touristnews.data.NewsRemoteMediator
import com.mogaka.touristnews.data.TouristRemoteMediator
import com.mogaka.touristnews.data.local.TouristNewsDatabase
import com.mogaka.touristnews.data.models.News
import com.mogaka.touristnews.data.models.Tourist
import com.mogaka.touristnews.network.NewsService
import com.mogaka.touristnews.network.TouristService
import com.mogaka.touristnews.utils.BASE_URL
import com.mogaka.touristnews.utils.HttpClient
import com.mogaka.touristnews.utils.MoshiBuilder
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesNewsService(retrofit: Retrofit): NewsService =
        retrofit.create(NewsService::class.java)


    @Provides
    fun providesTouristService(retrofit: Retrofit): TouristService =
        retrofit.create(TouristService::class.java)

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return HttpClient.create()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return MoshiBuilder.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideHttpClient())
        .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
        .build()


    @Provides
    @Singleton
    fun provideTouristNewsDatabase(@ApplicationContext context: Context): TouristNewsDatabase {
        return Room.databaseBuilder(
            context,
            TouristNewsDatabase::class.java,
            "tourist.db"
        ).build()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideNewsPager(db: TouristNewsDatabase, newsService: NewsService): Pager<Int, News> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = NewsRemoteMediator(
                db = db,
                newsService = newsService
            ),
            pagingSourceFactory = {
                db.newsDao.pagingSource()
            }
        )
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideTouristPager(
        db: TouristNewsDatabase,
        touristService: TouristService
    ): Pager<Int, Tourist> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = TouristRemoteMediator(
                db = db,
                touristService = touristService
            ),
            pagingSourceFactory = {
                db.touristDao.pagingSource()
            }
        )
    }
}