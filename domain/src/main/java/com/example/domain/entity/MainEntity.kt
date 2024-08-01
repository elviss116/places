package com.example.domain.entity

data class MainEntity<T>(
    val success: Boolean?,
    val message: String?,
    val data: T?
)
