package com.mogaka.touristnews.ui

import androidx.annotation.StringRes
import com.mogaka.touristnews.R

enum class Screens(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    News(title = R.string.news_screen),
    Tourists(title = R.string.tourist_screen),
    TouristDetails(title = R.string.tourist_details_screen)
}