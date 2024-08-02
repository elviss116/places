package com.example.data.network.mapper.placeRoom

import com.example.data.room.entity.EPlace
import com.example.domain.entity.placeDetail.PlaceDetailEntity
import kotlinx.coroutines.flow.Flow

interface PlaceRoomMapper {
    suspend fun placeLocalDataToDomain(flow: Flow<List<EPlace>>) : Flow<List<PlaceDetailEntity>>
}