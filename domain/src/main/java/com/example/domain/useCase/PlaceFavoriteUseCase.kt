package com.example.domain.useCase

import com.example.domain.entity.Either
import com.example.domain.entity.Failure
import com.example.domain.repository.PlaceFavoriteRepository
import javax.inject.Inject

class PlaceFavoriteUseCase @Inject constructor(
    private val repository: PlaceFavoriteRepository
) : BaseUseCase<String,PlaceFavoriteUseCase.Params>() {

    data class Params(
        val id: String,
        val idPlace: String,
        val name: String,
        val photo: String,
        val description: String,
        val address: String,
        val lat: String,
        val lng: String
    )

    override suspend fun run(params: Params): Either<Failure, String> = repository.addOrDeletePlace(
        id = params.id,
        idPlace = params.idPlace,
        name = params.name,
        photo = params.photo,
        description = params.description,
        address = params.address,
        lat = params.lat,
        lng = params.lng
    )
}