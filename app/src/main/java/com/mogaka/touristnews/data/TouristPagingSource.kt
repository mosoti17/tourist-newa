package com.mogaka.touristnews.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mogaka.touristnews.data.models.Tourist
import com.mogaka.touristnews.network.TouristService
import retrofit2.HttpException
import java.io.IOException

class TouristPagingSource(private val touristService: TouristService) :
    PagingSource<Int, Tourist>() {
    override fun getRefreshKey(state: PagingState<Int, Tourist>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Tourist> {
        val page = params.key ?: 1
        return try {
            val response = touristService.getTourists(page)
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