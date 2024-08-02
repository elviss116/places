package com.example.data.di

import com.example.data.network.source.IPlaceDetailEndPoint
import com.example.data.network.source.IPlaceFavoriteEndPoint
import com.example.data.network.source.IPlaceRoomEndPoint
import com.example.data.network.source.IPlacesEndPoint
import com.example.data.network.source.IVerifyFavoriteEndPoint
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

    @Binds
    abstract fun bindEndPointPlaceDetail(
        endPoint: IPlaceDetailEndPoint.IPlaceDetailEndPointImpl
    ) : IPlaceDetailEndPoint

    @Binds
    abstract fun bindEndPointPlaceFavorite(
        endPoint: IPlaceFavoriteEndPoint.IPlaceFavoriteEndPointImpl
    ) : IPlaceFavoriteEndPoint

    @Binds
    abstract fun bindEndPointPlaceRoom(
        endPoint: IPlaceRoomEndPoint.IPlaceRoomEndPointImpl
    ) : IPlaceRoomEndPoint

    @Binds
    abstract fun bindEndPointPlaceVerify(
        endPoint: IVerifyFavoriteEndPoint.IVerifyFavoriteEndPointImpl
    ) : IVerifyFavoriteEndPoint
}