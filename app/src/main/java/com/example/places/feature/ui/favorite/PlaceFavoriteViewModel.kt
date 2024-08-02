package com.example.places.feature.ui.favorite

import androidx.lifecycle.viewModelScope
import com.example.domain.entity.placeDetail.PlaceDetailEntity
import com.example.domain.useCase.GetPlaceRoomUseCase
import com.example.domain.useCase.PlaceFavoriteUseCase
import com.example.places.feature.base.BaseViewModel
import com.example.places.mapper.placeDetail.PlaceDetailModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceFavoriteViewModel @Inject constructor(
    private val useCasePlaceRoom: GetPlaceRoomUseCase,
    private val mapper: PlaceDetailModelMapper
) : BaseViewModel() {

    private val _state = MutableStateFlow<UIPlaceFavoriteState>(
        UIPlaceFavoriteState.OnPlaceFavoriteIsLoaded(listOf())
    )
    val state get() = _state.asStateFlow()

    init {
        executeUseCasePlaceRoom()
    }

    private fun executeUseCasePlaceRoom(){
        useCasePlaceRoom.invoke(viewModelScope,Any()){
            it.either(::handleUseCaseFailureFromBase,::handleSuccessPlaceRoomUseCase)
        }
    }

    private fun handleSuccessPlaceRoomUseCase(flow: Flow<List<PlaceDetailEntity>>){
        viewModelScope.launch(Dispatchers.IO) {
            flow.collectLatest { listPlace ->
                _state.update {
                    UIPlaceFavoriteState.OnPlaceFavoriteIsLoaded(
                        mapper.placeDetailDomainToPresentation(listPlace)
                    )
                }
            }
        }
    }
}