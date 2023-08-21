package com.mogaka.touristnews.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(newsViewModel: NewsViewModel, touristViewModel: TouristViewModel) {
    val tabData = listOf("News", "Tourists")
    val pagerState = rememberPagerState(0)
    Column(modifier = Modifier.fillMaxSize()) {
        TabLayout(tabData, pagerState)
        TabContent(tabData, pagerState, newsViewModel, touristViewModel)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayout(tabData: List<String>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        divider = {
            Spacer(modifier = Modifier.height(5.dp))
        },

        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        tabData.forEachIndexed { index, s ->
            Tab(selected = pagerState.currentPage == index, onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(index)
                }
            },
                text = {
                    Text(text = s)
                })
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabContent(
    tabData: List<String>,
    pagerState: PagerState,
    newsViewModel: NewsViewModel,
    touristViewModel: TouristViewModel
) {
    HorizontalPager(
        state = pagerState,
        pageCount = tabData.size,
        beyondBoundsPageCount = 1
    ) { index ->
        when (index) {
            0 -> {
                val newsArticles = newsViewModel.newsPager.collectAsLazyPagingItems()
                LazyColumn(Modifier.fillMaxSize()) {
                    items(newsArticles.itemCount) {
                        NewsCard(newsArticles[it]!!)
                    }
                }
            }

            1 -> {
                val tourists = touristViewModel.tourists.collectAsLazyPagingItems()
                LazyColumn(Modifier.fillMaxSize()) {
                    items(tourists.itemCount) {
                        TouristProfileCard(tourist = tourists[it]!!)
                    }
                }
            }

        }

    }

}