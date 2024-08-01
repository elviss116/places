package com.example.places.feature.base

interface UIBaseState {
    data class OnLoading(val show: Boolean) : UIBaseState
    data class OnTimeExpired(val show: Boolean): UIBaseState
}