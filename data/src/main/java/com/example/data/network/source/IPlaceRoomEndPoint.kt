package com.example.data.network.source

import com.example.data.room.dao.DaoPlace
import com.example.data.room.entity.EPlace
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IPlaceRoomEndPoint {
    suspend fun getPlaceRoom(): Flow<List<EPlace>>

    class IPlaceRoomEndPointImpl @Inject constructor(
        private val daoPlace: DaoPlace
    ) : IPlaceRoomEndPoint {
        override suspend fun getPlaceRoom(): Flow<List<EPlace>> = daoPlace.getAllPlace()
    }
}