package com.example.data.network.mapper.placeDetail

import com.example.data.entity.MainResponse
import com.example.data.entity.placeDetail.PlaceDetailResponse
import com.example.domain.entity.MainEntity
import com.example.domain.entity.placeDetail.PlaceDetailEntity
import javax.inject.Inject

class PlaceDetailMapperImpl @Inject constructor() : PlaceDetailMapper {
    override suspend fun placeDataToDomain(place: MainResponse<PlaceDetailResponse>): MainEntity<PlaceDetailEntity> {
        return MainEntity(
            success = place.success,
            message = place.message,
            data = place.data?.toDomain()
        )
    }
}