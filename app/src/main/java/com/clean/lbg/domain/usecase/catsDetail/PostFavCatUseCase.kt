package com.clean.lbg.domain.usecase.catsDetail

import com.clean.lbg.data.NetworkResult
import com.clean.lbg.domain.test.CallSuccessModel
import kotlinx.coroutines.flow.Flow

interface PostFavCatUseCase {
    suspend fun execute(imageId: String): Flow<NetworkResult<CallSuccessModel>>
}