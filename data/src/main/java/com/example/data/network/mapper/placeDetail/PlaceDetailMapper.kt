package com.example.data.network.mapper.placeDetail

import com.example.data.entity.MainResponse
import com.example.data.entity.placeDetail.PlaceDetailResponse
import com.example.domain.entity.MainEntity
import com.example.domain.entity.placeDetail.PlaceDetailEntity

interface PlaceDetailMapper {
    suspend fun placeDataToDomain(place: MainResponse<PlaceDetailResponse>) : MainEntity<PlaceDetailEntity>
}