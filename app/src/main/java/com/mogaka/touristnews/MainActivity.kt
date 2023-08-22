package com.mogaka.touristnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mogaka.touristnews.ui.MainScreen
import com.mogaka.touristnews.ui.NewsViewModel
import com.mogaka.touristnews.ui.TouristViewModel
import com.mogaka.touristnews.ui.theme.TouristNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val newsViewModel by viewModels<NewsViewModel>()
    private val touristViewModel by viewModels<TouristViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            TouristNewsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    MainScreen(newsViewModel, touristViewModel)

                }

            }

        }
    }
}



