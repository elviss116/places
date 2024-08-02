package com.example.places.feature.ui.favorite

import com.example.places.model.PlaceDetailModelView

sealed interface UIPlaceFavoriteState {
    data class OnPlaceFavoriteIsLoaded(val list: List<PlaceDetailModelView>) :UIPlaceFavoriteState
}