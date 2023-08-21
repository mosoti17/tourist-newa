package com.mogaka.touristnews.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.mogaka.touristnews.data.local.TouristNewsDatabase
import com.mogaka.touristnews.data.models.Tourist
import com.mogaka.touristnews.network.TouristService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class TouristRemoteMediator(
    private val db: TouristNewsDatabase,
    private val touristService: TouristService
) : RemoteMediator<Int, Tourist>() {
    private var nextKey = 0
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Tourist>): MediatorResult {
        try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    var items = 0
                    state.pages.forEach { items += it.data.size }

                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {
                        (lastItem.pagingId!! / state.config.pageSize) + 1
                    }
                }
            }
            val touristsResponse = touristService.getTourists(loadKey)
            nextKey = touristsResponse.page + 1
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.newsDao.clearAll()
                }
                db.touristDao.upsertAll(touristsResponse.data)
            }
            return MediatorResult.Success(
                endOfPaginationReached = touristsResponse.page == touristsResponse.totalPages
            )

        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }


}