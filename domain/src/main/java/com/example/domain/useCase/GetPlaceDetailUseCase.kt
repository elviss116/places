package com.example.domain.useCase

import com.example.domain.entity.Either
import com.example.domain.entity.Failure
import com.example.domain.entity.MainEntity
import com.example.domain.entity.placeDetail.PlaceDetailEntity
import com.example.domain.repository.PlaceDetailRepository
import javax.inject.Inject

class GetPlaceDetailUseCase @Inject constructor(
    private val repository: PlaceDetailRepository
): BaseUseCase<MainEntity<PlaceDetailEntity>,String>() {
    override suspend fun run(params: String): Either<Failure, MainEntity<PlaceDetailEntity>> =
        repository.getPlaceDetail(params)
}