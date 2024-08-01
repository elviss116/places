package com.example.data.network.source

import com.example.data.entity.MainResponse
import com.example.data.entity.placeDetail.PlaceDetailResponse
import com.example.data.network.endPoints.GetPlaceDetail
import com.example.data.network.remote.NetworkHandler
import com.example.domain.entity.Either
import com.example.domain.entity.Failure
import retrofit2.Retrofit
import javax.inject.Inject

interface IPlaceDetailEndPoint {
    suspend fun getDetail(id: String) : Either<Failure,MainResponse<PlaceDetailResponse>>

    class IPlaceDetailEndPointImpl @Inject constructor(
        private val retrofit: Retrofit,
        private val networkHandler: NetworkHandler
    ) : IPlaceDetailEndPoint {
        private val api by lazy { retrofit.create(GetPlaceDetail::class.java) }
        override suspend fun getDetail(id: String): Either<Failure, MainResponse<PlaceDetailResponse>> = networkHandler.callService {
            api.getPlaces(id)
        }
    }
}