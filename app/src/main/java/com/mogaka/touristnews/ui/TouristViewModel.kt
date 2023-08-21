package com.mogaka.touristnews.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.mogaka.touristnews.data.models.Tourist
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TouristViewModel @Inject constructor(pager: Pager<Int, Tourist>) : ViewModel() {
    val tourists = pager
        .flow
        .cachedIn(viewModelScope)
}