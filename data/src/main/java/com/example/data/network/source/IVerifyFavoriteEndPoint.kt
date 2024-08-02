package com.example.data.network.source

import com.example.data.room.dao.DaoPlace
import javax.inject.Inject

interface IVerifyFavoriteEndPoint {
    suspend fun verifyFavorite(id: String) : Int

    class IVerifyFavoriteEndPointImpl @Inject constructor(
        private val daoPlace: DaoPlace
    ) : IVerifyFavoriteEndPoint {
        override suspend fun verifyFavorite(id: String): Int = daoPlace.getPlaceById(id)

    }
}