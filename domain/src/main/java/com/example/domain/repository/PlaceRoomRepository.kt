package com.example.domain.repository

import com.example.domain.entity.Either
import com.example.domain.entity.Failure
import com.example.domain.entity.placeDetail.PlaceDetailEntity
import kotlinx.coroutines.flow.Flow

interface PlaceRoomRepository {
    suspend fun getPlaceLocal() : Either<Failure,Flow<List<PlaceDetailEntity>>>
}