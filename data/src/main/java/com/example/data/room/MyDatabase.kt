package com.example.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.room.dao.DaoPlace
import com.example.data.room.entity.EPlace

@Database(entities = [EPlace::class], version = 1, exportSchema = true)
abstract class MyDataBase : RoomDatabase(){
    abstract val daoPlace: DaoPlace
}