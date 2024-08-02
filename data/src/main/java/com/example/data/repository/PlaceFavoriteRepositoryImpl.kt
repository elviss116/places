package com.example.data.repository

import com.example.data.network.source.IPlaceFavoriteEndPoint
import com.example.domain.entity.Either
import com.example.domain.entity.Failure
import com.example.domain.repository.PlaceFavoriteRepository
import javax.inject.Inject

class PlaceFavoriteRepositoryImpl @Inject constructor(
    private val source: IPlaceFavoriteEndPoint
) : PlaceFavoriteRepository {
    override suspend fun addOrDeletePlace(
        id: String,
        idPlace: String,
        name: String,
        photo: String,
        description: String,
        address: String,
        lat: String,
        lng: String
    ): Either<Failure, String> = Either.Right(source.addOrDeletePlace(
        id = id,
        idPlace = idPlace,
        name = name,
        photo = photo,
        description = description,
        address = address,
        lat = lat,
        lng = lng
    ))
}