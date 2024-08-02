package com.example.domain.useCase

import com.example.domain.entity.Either
import com.example.domain.entity.Failure
import com.example.domain.repository.VerifyFavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VerifyFavoriteUseCase @Inject constructor(
    private val repository: VerifyFavoriteRepository
) : BaseUseCase<Flow<Int>, String>() {
    override suspend fun run(params: String): Either<Failure, Flow<Int>> = repository.verifyFavorite(params)
}