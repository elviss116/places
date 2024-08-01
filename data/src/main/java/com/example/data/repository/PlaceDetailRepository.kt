package com.example.data.repository

import com.example.data.network.mapper.placeDetail.PlaceDetailMapper
import com.example.data.network.source.IPlaceDetailEndPoint
import com.example.domain.entity.Either
import com.example.domain.entity.Failure
import com.example.domain.entity.MainEntity
import com.example.domain.entity.placeDetail.PlaceDetailEntity
import com.example.domain.repository.PlaceDetailRepository
import javax.inject.Inject

class PlaceDetailRepositoryImpl @Inject constructor(
    private val source: IPlaceDetailEndPoint,
    private val mapper: PlaceDetailMapper
) : PlaceDetailRepository{
    override suspend fun getPlaceDetail(id: String): Either<Failure, MainEntity<PlaceDetailEntity>> {
        return when(val response = source.getDetail(id)){
            is Either.Left -> Either.Left(response.a)
            is Either.Right -> Either.Right(mapper.placeDataToDomain(response.b))
        }
    }
}