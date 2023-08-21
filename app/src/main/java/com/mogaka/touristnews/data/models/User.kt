package com.mogaka.touristnews.data.models

import com.squareup.moshi.Json

data class User(
    @Json(name = "userid")
    var userid: Int,
    @Json(name = "name")
    var name: String,
    @Json(name = "profilepicture")
    var profilepicture: String? = null,

)
