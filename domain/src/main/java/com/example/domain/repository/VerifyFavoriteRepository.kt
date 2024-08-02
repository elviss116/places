package com.example.domain.repository

import com.example.domain.entity.Either
import com.example.domain.entity.Failure

interface VerifyFavoriteRepository {
    suspend fun verifyFavorite(id: String) : Either<Failure,Int>
}