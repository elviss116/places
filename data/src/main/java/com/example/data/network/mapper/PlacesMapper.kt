package com.example.data.network.mapper

import com.example.data.entity.MainResponse
import com.example.data.entity.places.PlacesResponse
import com.example.domain.entity.MainEntity
import com.example.domain.entity.movie.PlaceEntity

interface PlacesMapper {
    suspend fun placesDataToDomain(main: MainResponse<List<PlacesResponse>>) : MainEntity<List<PlaceEntity>>
}