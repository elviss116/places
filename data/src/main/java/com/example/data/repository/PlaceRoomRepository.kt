package com.example.data.repository

import com.example.data.network.mapper.placeRoom.PlaceRoomMapper
import com.example.data.network.source.IPlaceRoomEndPoint
import com.example.domain.entity.Either
import com.example.domain.entity.Failure
import com.example.domain.entity.placeDetail.PlaceDetailEntity
import com.example.domain.repository.PlaceRoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaceRoomRepositoryImpl @Inject constructor(
    private val source: IPlaceRoomEndPoint,
    private val mapper: PlaceRoomMapper
) : PlaceRoomRepository {
    override suspend fun getPlaceLocal(): Either<Failure, Flow<List<PlaceDetailEntity>>> {
        return Either.Right(mapper.placeLocalDataToDomain(source.getPlaceRoom()))
    }
}