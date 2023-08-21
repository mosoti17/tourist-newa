package com.mogaka.touristnews.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class News(
    @PrimaryKey(autoGenerate = true)
    @Json(name = "id")
    var id: Int,
    @Json(name = "title")
    var title: String? = null,
    @Json(name = "description")
    var description: String? = null,
    @Json(name = "location")
    var location: String? = null,
    @Json(name = "multiMedia")
    var multiMedia: List<MultiMedia> = listOf(),
    @Json(name = "createdat")
    var createdat: String? = null,
    @Json(name = "user")
    var user: User,
    @Json(name = "commentCount")
    var commentCount: Int
)
