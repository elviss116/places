package com.example.data.network.mapper.placeRoom

import com.example.data.room.entity.EPlace
import com.example.domain.entity.placeDetail.PlaceDetailEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlaceRoomMapperImpl @Inject constructor() : PlaceRoomMapper {
    override suspend fun placeLocalDataToDomain(flow: Flow<List<EPlace>>): Flow<List<PlaceDetailEntity>> {
        return flow.map { listPlace ->
            listPlace.map { place ->
                place.toDomain()
            }
        }
    }
}