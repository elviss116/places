package com.example.data.network.mapper.place

import com.example.data.entity.MainResponse
import com.example.data.entity.places.PlacesResponse
import com.example.domain.entity.MainEntity
import com.example.domain.entity.place.PlaceEntity

interface PlacesMapper {
    suspend fun placesDataToDomain(main: MainResponse<List<PlacesResponse>>) : MainEntity<List<PlaceEntity>>
}