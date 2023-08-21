package com.mogaka.touristnews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mogaka.touristnews.data.models.News
import com.mogaka.touristnews.data.models.Tourist
import com.mogaka.touristnews.utils.Converters

@Database(
    entities = [News::class, Tourist::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class TouristNewsDatabase : RoomDatabase() {
    abstract val newsDao: NewsDao
    abstract val touristDao: TouristDao
}