package com.clean.lbg.domain.usecase.catsDetail

import com.clean.lbg.data.NetworkResult
import com.clean.lbg.data.models.catDetails.PostFavCatModel
import com.clean.lbg.domain.mappers.mapSuccessData
import com.clean.lbg.domain.mappers.models.CallSuccessModel
import com.clean.lbg.domain.repositories.CatDetailsRepository
import com.clean.lbg.utils.Constants
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PostFavCatUseCaseImpl(private val catDetailsRepo: CatDetailsRepository) : PostFavCatUseCase {
    override suspend fun execute(imageId: String) = flow<NetworkResult<CallSuccessModel>> {
        emit(NetworkResult.Loading())
        val favCat = PostFavCatModel(imageId, Constants.SUB_ID)
        with(catDetailsRepo.postFavouriteCat(favCat)) {
            if (isSuccessful) {
                emit(NetworkResult.Success(this.body()?.mapSuccessData()))
                this.body()?.id?.let { catDetailsRepo.insertFavouriteCat(it, favCat.imageId) }
            } else {
                emit(NetworkResult.Error(this.errorBody().toString()))
            }
        }
    }.catch {
        emit(NetworkResult.Error(it.localizedMessage))
    }
}

