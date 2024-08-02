package com.example.places.feature.ui.placeDetail

import androidx.lifecycle.viewModelScope
import com.example.domain.entity.MainEntity
import com.example.domain.entity.placeDetail.PlaceDetailEntity
import com.example.domain.useCase.GetPlaceDetailUseCase
import com.example.domain.useCase.PlaceFavoriteUseCase
import com.example.domain.useCase.VerifyFavoriteUseCase
import com.example.places.feature.base.BaseViewModel
import com.example.places.model.PlaceDetailModelView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceDetailViewModel @Inject constructor(
    private val useCaseDetailPlace: GetPlaceDetailUseCase,
    private val useCaseAddFavorite: PlaceFavoriteUseCase,
    private val useCaseVerifyPlace: VerifyFavoriteUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow<UIPlaceDetailState>(UIPlaceDetailState.IDLE)
    val state get() = _state.asStateFlow()

    fun executeUseCasePlaceDetail(id: String){
        showLoading(true)
        useCaseDetailPlace.invoke(viewModelScope,id){
            it.either(::handleUseCaseFailureFromBase,::handleSuccessPlaceDetail)
        }
    }

    fun executeUseCaseVerifyFavoriteUseCase(id: String){
        useCaseVerifyPlace.invoke(viewModelScope,id){
            it.either(::handleUseCaseFailureFromBase,::handleSuccessVerifyFavoriteUseCase)
        }
    }

    fun executeUseCaseAddFavorite(place: PlaceDetailModelView){
        val params = PlaceFavoriteUseCase.Params(
            id = place.id,
            idPlace = place.idPlace,
            name = place.name,
            photo = place.photo,
            description = place.description,
            address = place.address,
            lat = place.lat,
            lng = place.lng
        )
        useCaseAddFavorite.invoke(viewModelScope,params){
            it.either(::handleUseCaseFailureFromBase,::handleSuccessAddFavoriteUseCase)
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
                            lat = pd.lat?:"0.0",
                            lng = pd.lng?:"0.0"
                        )
                    )
                }
            }
        }
    }

    private fun handleSuccessVerifyFavoriteUseCase(status: Flow<Int>){
        viewModelScope.launch {
            status.collectLatest { st ->
                _state.update {
                    UIPlaceDetailState.OnVerifyFavorite(st==1)
                }
            }
        }
    }

    private fun handleSuccessAddFavoriteUseCase(msg: String){
        _state.update {
            UIPlaceDetailState.OnAddOrDeleteFavorite(msg)
        }
    }
}