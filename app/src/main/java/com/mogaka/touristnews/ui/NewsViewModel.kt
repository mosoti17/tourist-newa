package com.mogaka.touristnews.ui

import androidx.lifecycle.ViewModel
import com.mogaka.touristnews.data.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(newsRepository: NewsRepository) : ViewModel() {
    val newsPager = newsRepository.getNewsArticles()


}