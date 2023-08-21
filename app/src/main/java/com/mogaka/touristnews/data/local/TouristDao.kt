package com.mogaka.touristnews.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mogaka.touristnews.data.models.Tourist

@Dao

interface TouristDao {
    @Upsert
    suspend fun upsertAll(newsArticles: List<Tourist>)

    @Query("SELECT *  FROM tourist")
    fun pagingSource(): PagingSource<Int, Tourist>

    @Query("DELETE FROM tourist")
    suspend fun clearAll()
}