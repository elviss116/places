package com.example.domain.repository

import com.example.domain.entity.Either
import com.example.domain.entity.Failure
import com.example.domain.entity.MainEntity
import com.example.domain.entity.movie.PlaceEntity

interface PlacesRepository {
    suspend fun getPlaces(): Either<Failure,MainEntity<PlaceEntity>>
}