package com.example.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.entity.placeDetail.PlaceDetailEntity

@Entity(tableName = "place")
data class EPlace(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "idPlace")
    val idPlace: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "photo")
    val photo: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "lat")
    val lat: String,
    @ColumnInfo(name = "lng")
    val lng: String,
) {
    fun toDomain() : PlaceDetailEntity {
        return PlaceDetailEntity(
            id = id,
            idPlace = idPlace,
            name = name,
            photo = photo,
            description = description,
            address = address,
            lat = lat,
            lng = lng
        )
    }
}
