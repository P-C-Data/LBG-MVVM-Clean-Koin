package com.clean.lbg.domain.usecase.cats

import com.clean.lbg.data.NetworkResult
import com.clean.lbg.domain.test.CatDataModel
import kotlinx.coroutines.flow.Flow

interface GetCatsUseCase {
    suspend fun execute(): Flow<NetworkResult<List<CatDataModel>>>
}