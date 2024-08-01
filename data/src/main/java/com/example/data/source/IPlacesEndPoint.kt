package com.example.data.source

import com.example.data.entity.MainResponse
import com.example.data.entity.places.PlacesResponse
import com.example.data.network.endPoints.GetPlaces
import com.example.data.network.remote.NetworkHandler
import com.example.domain.entity.Either
import com.example.domain.entity.Failure
import retrofit2.Retrofit
import javax.inject.Inject

interface IPlacesEndPoint {
    suspend fun getPlaces() : Either<Failure,MainResponse<List<PlacesResponse>>>

    class IPlacesEndPointImpl @Inject constructor(
        private val retrofit: Retrofit,
        private val networkHandler: NetworkHandler
    ): IPlacesEndPoint {
        private val api by lazy { retrofit.create(GetPlaces::class.java) }
        override suspend fun getPlaces(): Either<Failure, MainResponse<List<PlacesResponse>>> = networkHandler.callService {
            api.getPlaces()
        }
    }
}