package com.example.data.entity.places

import com.example.domain.entity.place.PlaceEntity
import com.google.gson.annotations.SerializedName

data class PlacesResponse(
    @SerializedName("idPlace")
    val id: String?,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
) {
    fun placesToEntity() : PlaceEntity {
        return PlaceEntity(
            id = id,
            photo = photo,
            name = name,
            description = description
        )
    }
}
