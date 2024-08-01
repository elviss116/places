package com.example.places.di

import com.example.places.mapper.places.PlacesModelMapper
import com.example.places.mapper.places.PlacesModelMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class MapperModule {

    @ViewModelScoped
    @Binds
    abstract fun bindMapperPlace(
        mapperImpl: PlacesModelMapperImpl
    ) : PlacesModelMapper
}