package com.example.data.di

import com.example.data.network.mapper.PlacesMapper
import com.example.data.network.mapper.PlacesMapperImpl
import com.example.data.source.IPlacesEndPoint
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
}