package com.mogaka.touristnews

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TouristNewsApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}