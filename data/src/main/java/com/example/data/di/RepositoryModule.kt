package com.example.data.di

import com.example.data.network.mapper.PlacesMapper
import com.example.data.network.mapper.PlacesMapperImpl
import com.example.data.repository.PlacesRepositoryImpl
import com.example.data.source.IPlacesEndPoint
import com.example.domain.repository.PlacesRepository
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
}