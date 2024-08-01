package com.example.data.di

import com.example.data.network.utils.ConnectionUtils
import com.example.data.network.utils.ConnectionUtilsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ConnectionUtilModule {
    @Binds
    abstract fun bindConnectionUtils(
        utils: ConnectionUtilsImpl
    ): ConnectionUtils
}