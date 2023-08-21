package com.mogaka.touristnews.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.mogaka.touristnews.R
import com.mogaka.touristnews.data.models.News

@Composable
fun NewsScreen(
    newsArticles: LazyPagingItems<News>
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = newsArticles.loadState) {
        if (newsArticles.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (newsArticles.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (newsArticles.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(newsArticles.itemCount) { i ->
                    val news = newsArticles[i]
                    if (news != null) {
                        NewsCard(data = news)
                    }

                }
                item {
                    if (newsArticles.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@Composable
fun NewsCard(
    data: News
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val context = LocalContext.current
            val imageUrl = if (data.multiMedia.isNotEmpty()) data.multiMedia[0].url else ""
            val imageRequest = ImageRequest.Builder(context)
                .data(imageUrl)
                .memoryCacheKey(imageUrl)
                .diskCacheKey(imageUrl)
                .placeholder(R.drawable.loading_img)
                .error(R.drawable.ic_connection_error)
                .fallback(R.drawable.loading_img)
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build()
            AsyncImage(
                model = imageRequest,
                contentDescription = data.title ?: "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.aspectRatio(1.7777f)
            )

            Text(
                data.title ?: "",

                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                data.description ?: "",

                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                data.createdat ?: "",

                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}