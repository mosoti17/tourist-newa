package com.mogaka.touristnews.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.google.android.material.animation.AnimationUtils.lerp
import com.mogaka.touristnews.R
import com.mogaka.touristnews.data.models.News
import com.mogaka.touristnews.utils.Date
import kotlin.math.absoluteValue

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
                Toast.LENGTH_SHORT
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
            ImagePager(data)


            Text(
                data.title ?: "",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                data.description ?: "",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                "By ${data.user.name} on ${Date.toHumanReadable(data.createdat)}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Right
            )

        }
    }
}

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagePager(news: News) {

    val pagerState = rememberPagerState()

    HorizontalPager(
        modifier = Modifier.fillMaxWidth(),
        state = pagerState,
        pageCount = news.multiMedia.size
    ) { position ->
        Card(
            Modifier

                .graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - position) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue

                    alpha = lerp(
                        0.5f,
                        1f,
                        1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
        ) {
            val context = LocalContext.current
            val imageUrl = if (news.multiMedia.isNotEmpty()) news.multiMedia[position].url else ""
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
                contentDescription = news.title ?: "",
                contentScale = ContentScale.Fit,
                modifier = Modifier.aspectRatio(1.6f)
            )
        }
    }
    if (news.multiMedia.size > 1) {
        HorizontalPagerIndicator(pagerState = pagerState, pageCount = news.multiMedia.size)
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    activeColor: Color = Color.Black,
    inactiveColor: Color = Color.Gray,
    pageCount: Int
) {
    val currentPage = pagerState.currentPage

    val indicatorPositions = (0 until pageCount).map {
        it
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        indicatorPositions.forEach { position ->
            val isActive = position == currentPage
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .offset(x = position.dp)
                    .background(if (isActive) activeColor else inactiveColor, shape = CircleShape)

            )
        }
    }
}