package com.example.domain.useCase

import com.example.domain.entity.Either
import com.example.domain.entity.Failure
import com.example.domain.entity.MainEntity
import com.example.domain.entity.movie.PlaceEntity
import com.example.domain.repository.PlacesRepository
import javax.inject.Inject

class GetPlacesUseCase @Inject constructor(
    private val repository: PlacesRepository
) : BaseUseCase<MainEntity<PlaceEntity>,Any>() {
    override suspend fun run(params: Any): Either<Failure, MainEntity<PlaceEntity>> =
        repository.getPlaces()
}