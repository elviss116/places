package com.example.places.feature.ui.place

import com.example.places.model.PlacesModelView

sealed interface UIPlaceState {
    data class OnPlacesIsLoaded(val list: List<PlacesModelView>) : UIPlaceState
}