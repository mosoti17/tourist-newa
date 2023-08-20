package com.mogaka.touristnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.mogaka.touristnews.ui.NewsCard
import com.mogaka.touristnews.ui.NewsViewModel
import com.mogaka.touristnews.ui.theme.TouristNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val newsViewModel by viewModels<NewsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TouristNewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val newsArticles = newsViewModel.newsPager.collectAsLazyPagingItems()
                    LazyColumn(Modifier.fillMaxSize()) {
                        items(newsArticles.itemCount) {
                            NewsCard(newsArticles[it]!!)
                        }
                    }
                }

            }

        }
    }
}



