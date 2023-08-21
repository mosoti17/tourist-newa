package com.mogaka.touristnews.ui

import androidx.lifecycle.ViewModel
import com.mogaka.touristnews.data.repo.TouristRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class TouristViewModel @Inject constructor(touristRepository: TouristRepository): ViewModel(){
    val tourists = touristRepository.getTourists();
}