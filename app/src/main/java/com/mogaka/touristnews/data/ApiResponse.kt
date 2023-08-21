package com.mogaka.touristnews.data

import com.squareup.moshi.Json

data class ApiResponse<T>(
    @Json(name = "page")
    var page: Int,
    @Json(name = "per_page")
    var perPage: Int,
    @Json(name = "totalrecord")
    var totalrecord: Int,
    @Json(name = "total_pages")
    var totalPages: Int,
    @Json(name = "data")
    var data: List<T> = listOf()

)
