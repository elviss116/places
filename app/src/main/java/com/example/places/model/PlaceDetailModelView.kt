package com.example.places.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaceDetailModelView(
    val id: String,
    val idPlace: String,
    val name: String,
    val photo: String,
    val description: String,
    val address: String,
    val lat: String,
    val lng: String
) : Parcelable
