package com.example.data.repository

import com.example.data.network.mapper.PlacesMapper
import com.example.data.source.IPlacesEndPoint
import com.example.domain.entity.Either
import com.example.domain.entity.Failure
import com.example.domain.entity.MainEntity
import com.example.domain.entity.movie.PlaceEntity
import com.example.domain.repository.PlacesRepository
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(
    private val source: IPlacesEndPoint,
    private val mapper: PlacesMapper
) : PlacesRepository {
    override suspend fun getPlaces(): Either<Failure, MainEntity<PlaceEntity>> {
        return when(val response = source.getPlaces()){
            is Either.Left -> Either.Left(response.a)
            is Either.Right -> Either.Right(mapper.placesDataToDomain(response.b))
        }
    }
}