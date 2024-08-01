package com.example.data.entity

import com.google.gson.annotations.SerializedName


data class MainResponse<T>(
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: T?
)
