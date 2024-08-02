package com.example.data.di

import android.app.Application
import androidx.room.Room
import com.example.data.room.MyDataBase
import com.example.data.room.dao.DaoPlace
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun providerDatabase(application: Application) : MyDataBase{
        return Room.databaseBuilder(application,MyDataBase::class.java,"MyDB")
            .build()
    }

    @Provides
    fun providerPlaceDao(db: MyDataBase) : DaoPlace = db.daoPlace
}