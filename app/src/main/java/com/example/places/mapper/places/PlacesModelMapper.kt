package com.example.places.mapper.places

import com.example.domain.entity.movie.PlaceEntity
import com.example.places.model.PlacesModelView

interface PlacesModelMapper {
    suspend fun placesDomainToPresentation(places: List<PlaceEntity>) : List<PlacesModelView>
}