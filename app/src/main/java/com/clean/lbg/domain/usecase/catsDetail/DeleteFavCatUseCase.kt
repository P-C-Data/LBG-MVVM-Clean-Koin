package com.clean.lbg.domain.usecase.catsDetail

import com.clean.lbg.data.NetworkResult
import com.clean.lbg.domain.test.CallSuccessModel
import kotlinx.coroutines.flow.Flow

interface DeleteFavCatUseCase {
    suspend fun execute(imageId: String, favId: Int): Flow<NetworkResult<CallSuccessModel>>
}