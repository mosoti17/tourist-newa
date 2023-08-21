package com.mogaka.touristnews

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mogaka.touristnews.data.Tourist
import com.mogaka.touristnews.network.TouristService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TouristRepository @Inject constructor(private val touristService: TouristService) {
    fun getTourists(): Flow<PagingData<Tourist>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TouristPagingSource(touristService) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 40
    }
}