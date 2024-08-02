package com.example.places.mapper.placeDetail

import com.example.domain.entity.placeDetail.PlaceDetailEntity
import com.example.places.model.PlaceDetailModelView
import javax.inject.Inject

class PlaceDetailModelMapperImpl @Inject constructor() : PlaceDetailModelMapper {
    override suspend fun placeDetailDomainToPresentation(list: List<PlaceDetailEntity>): List<PlaceDetailModelView> {
        return list.map { pd ->
            PlaceDetailModelView(
                id = pd.id.orEmpty(),
                idPlace = pd.idPlace.orEmpty(),
                name = pd.name.orEmpty(),
                photo = pd.photo.orEmpty(),
                description = pd.description.orEmpty(),
                address = pd.address.orEmpty(),
                lat = pd.lat?:"0.0",
                lng = pd.lng?:"0.0"
            )
        }
    }
}