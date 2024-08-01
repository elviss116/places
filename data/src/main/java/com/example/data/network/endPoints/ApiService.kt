package com.example.data.network.endPoints

import com.example.data.entity.MainResponse
import com.example.data.entity.places.PlacesResponse
import retrofit2.Response
import retrofit2.http.GET

interface GetPlaces {
    @GET("api-place/listPlaces.php")
    suspend fun getPlaces(): Response<MainResponse<PlacesResponse>>
}