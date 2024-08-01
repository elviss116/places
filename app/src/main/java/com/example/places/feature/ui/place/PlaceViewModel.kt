package com.example.places.feature.ui.place

import androidx.lifecycle.viewModelScope
import com.example.domain.entity.MainEntity
import com.example.domain.entity.movie.PlaceEntity
import com.example.domain.useCase.GetPlacesUseCase
import com.example.places.feature.base.BaseViewModel
import com.example.places.mapper.places.PlacesModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceViewModel @Inject constructor(
    private val useCasePlace: GetPlacesUseCase,
    private val mapper: PlacesModelMapper
) : BaseViewModel(){

    private val _state = MutableStateFlow<UIPlaceState>(UIPlaceState.OnPlacesIsLoaded(listOf()))
    val state get() = _state.asStateFlow()

    fun executeUseCaseGetPlace(){
        useCasePlace.invoke(viewModelScope,Any()){
            it.either(::handleUseCaseFailureFromBase,::handleSuccessUseCasePlace)
        }
    }

    private fun handleSuccessUseCasePlace(places: MainEntity<List<PlaceEntity>>){
        viewModelScope.launch {
            _state.update {
                UIPlaceState.OnPlacesIsLoaded(
                    mapper.placesDomainToPresentation(places.data?: listOf())
                )
            }
        }
    }
}