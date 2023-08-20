package com.mogaka.touristnews

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mogaka.touristnews.data.News
import com.mogaka.touristnews.network.NewsService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class NewsRepository @Inject constructor(private val newsService: NewsService) {
    fun getNewsArticles(): Flow<PagingData<News>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(newsService) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}