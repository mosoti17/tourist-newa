package com.mogaka.touristnews.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
@Entity
data class Tourist(
    @PrimaryKey(autoGenerate = false)
    var pagingId: Int? = null,
    @Json(name = "id")
    var id: Int,
    @Json(name = "tourist_name")
    var touristName: String? = null,
    @Json(name = "tourist_email")
    var touristEmail: String,
    @Json(name = "tourist_location")
    var touristLocation: String,
    @Json(name = "createdat")
    var createdat: LocalDateTime? = null
) : Parcelable
