package com.example.places.feature.ui.placeDetail

import androidx.lifecycle.viewModelScope
import com.example.domain.entity.MainEntity
import com.example.domain.entity.placeDetail.PlaceDetailEntity
import com.example.domain.useCase.GetPlaceDetailUseCase
import com.example.places.feature.base.BaseViewModel
import com.example.places.model.PlaceDetailModelView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceDetailViewModel @Inject constructor(
    private val useCaseDetailPlace: GetPlaceDetailUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow<UIPlaceDetailState>(UIPlaceDetailState.IDLE)
    val state get() = _state.asStateFlow()

    fun executeUseCasePlaceDetail(id: String){
        showLoading(true)
        useCaseDetailPlace.invoke(viewModelScope,id){
            it.either(::handleUseCaseFailureFromBase,::handleSuccessPlaceDetail)
        }
    }

    private fun handleSuccessPlaceDetail(place: MainEntity<PlaceDetailEntity>){
        showLoading(false)
        place.data?.let { pd ->
            viewModelScope.launch {
                _state.update {
                    UIPlaceDetailState.OnDetailIsLoaded(
                        detail = PlaceDetailModelView(
                            id = pd.id.orEmpty(),
                            idPlace = place.data?.idPlace.orEmpty(),
                            name = pd.name.orEmpty(),
                            photo = pd.photo.orEmpty(),
                            description = pd.description.orEmpty(),
                            address = pd.address.orEmpty(),
                            lat = pd.lat.orEmpty(),
                            lng = pd.lng.orEmpty()
                        )
                    )
                }
            }
        }
    }
}