package com.clean.lbg.domain.usecase.cats

import com.clean.lbg.data.NetworkResult
import com.clean.lbg.domain.mappers.models.CatDataModel
import kotlinx.coroutines.flow.Flow

interface GetFavCatsUseCase {
    suspend fun execute(): Flow<NetworkResult<List<CatDataModel>>>
}