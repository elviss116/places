package com.example.data.entity.placeDetail

import com.example.domain.entity.placeDetail.PlaceDetailEntity
import com.google.gson.annotations.SerializedName

data class PlaceDetailResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("idPlace")
    val idPlace: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String,
) {
    fun toDomain() : PlaceDetailEntity{
        return PlaceDetailEntity(
            id = id,
            idPlace = idPlace,
            name = name,
            photo = photo,
            description = description,
            address = address,
            lat = lat,
            lng = lng
        )
    }
}
