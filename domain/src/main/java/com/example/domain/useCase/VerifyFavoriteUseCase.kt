package com.example.domain.useCase

import com.example.domain.entity.Either
import com.example.domain.entity.Failure
import com.example.domain.repository.VerifyFavoriteRepository
import javax.inject.Inject

class VerifyFavoriteUseCase @Inject constructor(
    private val repository: VerifyFavoriteRepository
) : BaseUseCase<Int, String>() {
    override suspend fun run(params: String): Either<Failure, Int> = repository.verifyFavorite(params)
}