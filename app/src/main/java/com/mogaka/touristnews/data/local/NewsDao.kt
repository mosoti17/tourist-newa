package com.mogaka.touristnews.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mogaka.touristnews.data.models.News

@Dao
interface NewsDao {
    @Upsert
    suspend fun upsertAll(newsArticles: List<News>)

    @Query("SELECT *  FROM news")
    fun pagingSource(): PagingSource<Int, News>

    @Query("DELETE FROM news")
    suspend fun clearAll()
}