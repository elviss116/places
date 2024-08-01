package com.example.domain.repository

import com.example.domain.entity.Either
import com.example.domain.entity.Failure
import com.example.domain.entity.MainEntity
import com.example.domain.entity.placeDetail.PlaceDetailEntity

interface PlaceDetailRepository {
    suspend fun getPlaceDetail(id: String) : Either<Failure,MainEntity<PlaceDetailEntity>>
}