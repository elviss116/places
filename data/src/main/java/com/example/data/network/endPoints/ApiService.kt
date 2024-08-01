package com.example.data.network.endPoints

import com.example.data.entity.MainResponse
import com.example.data.entity.placeDetail.PlaceDetailResponse
import com.example.data.entity.places.PlacesResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface GetPlaces {
    @GET("api-place/listPlaces.php")
    suspend fun getPlaces(): Response<MainResponse<List<PlacesResponse>>>
}

interface GetPlaceDetail {
    @FormUrlEncoded
    @POST("api-place/placeDetail.php")
    suspend fun getPlaces(
        @Field(value = "idPlace") id: String
    ): Response<MainResponse<PlaceDetailResponse>>
}