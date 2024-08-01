package com.example.data.di

import com.example.data.source.IPlacesEndPoint
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class EndPointsModule {

    @Binds
    abstract fun bindEndPointPlaces(
        endPoint: IPlacesEndPoint.IPlacesEndPointImpl
    ) : IPlacesEndPoint
}