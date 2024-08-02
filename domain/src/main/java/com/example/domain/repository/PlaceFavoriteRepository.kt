package com.example.domain.repository

import com.example.domain.entity.Either
import com.example.domain.entity.Failure

interface PlaceFavoriteRepository {
    suspend fun addOrDeletePlace(
        id: String,
        idPlace: String,
        name: String,
        photo: String,
        description: String,
        address: String,
        lat: String,
        lng: String
    ) : Either<Failure,String>
}