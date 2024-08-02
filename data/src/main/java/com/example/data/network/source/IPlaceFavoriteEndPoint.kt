package com.example.data.network.source

import com.example.data.room.dao.DaoPlace
import com.example.data.room.entity.EPlace
import javax.inject.Inject

interface IPlaceFavoriteEndPoint {
    suspend fun addOrDeletePlace(
        id: String,
        idPlace: String,
        name: String,
        photo: String,
        description: String,
        address: String,
        lat: String,
        lng: String
    ) : String

    class IPlaceFavoriteEndPointImpl @Inject constructor(
        private val daoPlace: DaoPlace
    ) : IPlaceFavoriteEndPoint {
        override suspend fun addOrDeletePlace(
            id: String,
            idPlace: String,
            name: String,
            photo: String,
            description: String,
            address: String,
            lat: String,
            lng: String
        ): String {
            val count = daoPlace.getPlaceById(id)
            return when(count){
                1 -> {
                    daoPlace.deletePlaceId(id)
                    "Eliminado de favoritos"
                }
                else -> {
                    daoPlace.insertPlace(
                        EPlace(
                            id = id,
                            idPlace = idPlace,
                            name = name,
                            photo = photo,
                            description = description,
                            address = address,
                            lat = lat,
                            lng = lng
                        )
                    )
                    "Se agregó a favoritos"
                }
            }
        }

    }
}