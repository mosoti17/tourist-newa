package com.mogaka.touristnews

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mogaka.touristnews.data.News
import com.mogaka.touristnews.network.NewsService
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(private val newsService: NewsService) :
    PagingSource<Int, News>() {
    override fun getRefreshKey(state: PagingState<Int, News>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        val page = params.key ?: 1
        return try {
            val response = newsService.getFeeds(page)
            val pageNumber = response.page
            LoadResult.Page(
                data = response.data,
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (pageNumber == response.totalPages) null else (pageNumber) + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}