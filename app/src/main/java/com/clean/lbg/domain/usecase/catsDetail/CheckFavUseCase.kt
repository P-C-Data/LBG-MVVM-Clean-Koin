package com.clean.lbg.domain.usecase.catsDetail

import kotlinx.coroutines.flow.Flow

interface CheckFavUseCase {
    suspend fun execute(imageId: String): Flow<Int?>
}