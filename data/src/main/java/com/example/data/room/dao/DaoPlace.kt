package com.example.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.room.entity.EPlace
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoPlace {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlace(place: EPlace): Long

    @Query("delete from place where id=:id")
    suspend fun deletePlaceId(id: String)

    @Query("select * from place")
    fun getAllPlace() : Flow<List<EPlace>>

    @Query("select count(*) from place where id=:id")
    suspend fun getPlaceById(id: String) : Int

    @Query("select count(*) from place where id=:id")
    fun getPlaceByIdFlow(id: String) : Flow<Int>
}