package com.example.domain.useCase

import com.example.domain.entity.Either
import com.example.domain.entity.Failure
import com.example.domain.entity.placeDetail.PlaceDetailEntity
import com.example.domain.repository.PlaceRoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlaceRoomUseCase @Inject constructor(
    private val repository: PlaceRoomRepository
) : BaseUseCase<Flow<List<PlaceDetailEntity>>,Any>(){
    override suspend fun run(params: Any): Either<Failure, Flow<List<PlaceDetailEntity>>> =
        repository.getPlaceLocal()
}