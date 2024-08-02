package com.example.data.repository

import com.example.data.network.source.IVerifyFavoriteEndPoint
import com.example.domain.entity.Either
import com.example.domain.entity.Failure
import com.example.domain.repository.VerifyFavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VerifyFavoriteRepositoryImpl @Inject constructor(
    private val source: IVerifyFavoriteEndPoint
) : VerifyFavoriteRepository {
    override suspend fun verifyFavorite(id: String): Either<Failure, Flow<Int>> {
        return Either.Right(source.verifyFavorite(id))
    }
}