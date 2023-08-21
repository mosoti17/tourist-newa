package com.mogaka.touristnews.data

import com.squareup.moshi.Json
import java.util.Date

data class Tourist(
    @Json(name = "id")
    var id: Int,
    @Json(name = "tourist_name")
    var touristName: String? = null,
    @Json(name = "tourist_email")
    var touristEmail: String,
    @Json(name = "tourist_location")
    var touristLocation: String,
    @Json(name = "createdat")
    var createdat: String? = null
)
