package com.mogaka.touristnews.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.mogaka.touristnews.data.models.News
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(pager: Pager<Int, News>) : ViewModel() {
    val newsPager = pager
        .flow
        .cachedIn(viewModelScope)


}