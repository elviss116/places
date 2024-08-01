package com.example.places.feature.ui.place

import com.example.domain.useCase.GetPlacesUseCase
import com.example.places.feature.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlaceViewModel @Inject constructor(
    private val useCasePlace: GetPlacesUseCase
) : BaseViewModel(){


}