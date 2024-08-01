package com.example.data.network.mapper.place

import com.example.data.entity.MainResponse
import com.example.data.entity.places.PlacesResponse
import com.example.domain.entity.MainEntity
import com.example.domain.entity.place.PlaceEntity
import javax.inject.Inject

class PlacesMapperImpl @Inject constructor() : PlacesMapper {
    override suspend fun placesDataToDomain(main: MainResponse<List<PlacesResponse>>): MainEntity<List<PlaceEntity>> {
        return MainEntity(
            success = main.success,
            message = main.message,
            data = main.data?.let { places ->
                places.map { place ->
                    place.placesToEntity()
                }
            }
        )
    }
}