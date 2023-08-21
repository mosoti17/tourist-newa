package com.mogaka.touristnews.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date


class JavaUtilDateAdapter : JsonAdapter<Date?>() {
    private val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    override fun fromJson(reader: JsonReader): Date? {
        val dateString: String = reader.nextString()
        return dateFormat.parse(dateString)
    }

    override fun toJson(writer: JsonWriter, value: Date?) {
        writer.value(dateFormat.format(value!!))
    }

}