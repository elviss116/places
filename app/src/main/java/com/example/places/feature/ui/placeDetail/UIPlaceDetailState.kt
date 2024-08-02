package com.example.places.feature.ui.placeDetail

import com.example.places.model.PlaceDetailModelView

interface UIPlaceDetailState {
    data object  IDLE : UIPlaceDetailState
    data class OnDetailIsLoaded(val detail: PlaceDetailModelView) : UIPlaceDetailState
    data class OnVerifyFavorite(val isFavorite: Boolean) : UIPlaceDetailState
    data class OnAddOrDeleteFavorite(val msg: String) : UIPlaceDetailState
}