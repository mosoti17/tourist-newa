package com.mogaka.touristnews.data.models

import com.squareup.moshi.Json
import java.util.Date

data class MultiMedia(
    @Json(name = "id")
    var id: Int,
    @Json(name = "title")
    var title: String? = null,
    @Json(name = "name")
    var name: String,
    @Json(name = "description")
    var description: String? = null,
    @Json(name = "url")
    var url: String,
    @Json(name = "createat")
    var createat: String

)