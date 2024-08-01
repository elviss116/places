package com.example.domain.entity.placeDetail

data class PlaceDetailEntity(
    val id: String,
    val idPlace: String,
    val name: String,
    val photo: String,
    val description: String,
    val address: String,
    val lat: String,
    val lng: String
)