package com.mogaka.touristnews.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object Date {
    fun toHumanReadable(date: LocalDateTime?): String {
        val locale = Locale.getDefault()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", locale)
        return if (date != null) {
            date.format(formatter)
        } else {
            ""
        }
    }
}