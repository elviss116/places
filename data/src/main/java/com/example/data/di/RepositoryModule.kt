package com.example.data.di

import com.example.data.repository.PlaceDetailRepositoryImpl
import com.example.data.repository.PlaceFavoriteRepositoryImpl
import com.example.data.repository.PlaceRoomRepositoryImpl
import com.example.data.repository.PlacesRepositoryImpl
import com.example.data.repository.VerifyFavoriteRepositoryImpl
import com.example.domain.repository.PlaceDetailRepository
import com.example.domain.repository.PlaceFavoriteRepository
import com.example.domain.repository.PlaceRoomRepository
import com.example.domain.repository.PlacesRepository
import com.example.domain.repository.VerifyFavoriteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepositoryPlaces(
        repositoryImpl: PlacesRepositoryImpl
    ) : PlacesRepository

    @Binds
    abstract fun bindRepositoryPlaceDetail(
        repositoryImpl: PlaceDetailRepositoryImpl
    ) : PlaceDetailRepository

    @Binds
    abstract fun bindRepositoryPlaceFavorite(
        repositoryImpl: PlaceFavoriteRepositoryImpl
    ) : PlaceFavoriteRepository

    @Binds
    abstract fun bindRepositoryPlaceRoom(
        repositoryImpl: PlaceRoomRepositoryImpl
    ) : PlaceRoomRepository

    @Binds
    abstract fun bindRepositoryPlaceVerify(
        repositoryImpl: VerifyFavoriteRepositoryImpl
    ) : VerifyFavoriteRepository
}