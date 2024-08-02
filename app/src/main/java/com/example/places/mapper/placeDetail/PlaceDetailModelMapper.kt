package com.example.places.mapper.placeDetail

import com.example.domain.entity.placeDetail.PlaceDetailEntity
import com.example.places.model.PlaceDetailModelView

interface PlaceDetailModelMapper {
    suspend fun placeDetailDomainToPresentation(list: List<PlaceDetailEntity>) : List<PlaceDetailModelView>
}