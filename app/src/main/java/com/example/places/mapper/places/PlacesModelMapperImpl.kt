package com.example.places.mapper.places

import com.example.domain.entity.movie.PlaceEntity
import com.example.places.model.PlacesModelView
import javax.inject.Inject

class PlacesModelMapperImpl @Inject constructor() : PlacesModelMapper {
    override suspend fun placesDomainToPresentation(places: List<PlaceEntity>): List<PlacesModelView> =
        places.map { place ->
            PlacesModelView(
                id = place.id.orEmpty(),
                title = place.name.orEmpty(),
                photo = place.photo.orEmpty(),
                description = place.description.orEmpty()
            )
        }
}