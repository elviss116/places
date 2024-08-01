package com.example.data.di

import com.example.data.network.mapper.place.PlacesMapper
import com.example.data.network.mapper.place.PlacesMapperImpl
import com.example.data.network.mapper.placeDetail.PlaceDetailMapper
import com.example.data.network.mapper.placeDetail.PlaceDetailMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindMapperPlaces(
        mapperImpl: PlacesMapperImpl
    ) : PlacesMapper

    @Binds
    abstract fun bindMapperPlaceDetail(
        mapperImpl: PlaceDetailMapperImpl
    ) : PlaceDetailMapper
}