package com.example.data.network.mapper

import com.example.data.entity.MainResponse
import com.example.data.entity.places.PlacesResponse
import com.example.domain.entity.MainEntity
import com.example.domain.entity.movie.PlaceEntity
import javax.inject.Inject

class PlacesMapperImpl @Inject constructor() : PlacesMapper {
    override suspend fun placesDataToDomain(main: MainResponse<PlacesResponse>): MainEntity<PlaceEntity> {
        return MainEntity(
            success = main.success,
            message = main.message,
            data = main.data?.placesToEntity()
        )
    }
}