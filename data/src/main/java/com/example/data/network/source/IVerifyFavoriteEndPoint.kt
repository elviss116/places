package com.example.data.network.source

import com.example.data.room.dao.DaoPlace
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IVerifyFavoriteEndPoint {
    suspend fun verifyFavorite(id: String) : Flow<Int>

    class IVerifyFavoriteEndPointImpl @Inject constructor(
        private val daoPlace: DaoPlace
    ) : IVerifyFavoriteEndPoint {
        override suspend fun verifyFavorite(id: String): Flow<Int> = daoPlace.getPlaceByIdFlow(id)

    }
}