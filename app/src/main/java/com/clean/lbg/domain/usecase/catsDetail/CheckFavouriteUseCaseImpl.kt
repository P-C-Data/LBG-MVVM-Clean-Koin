package com.clean.lbg.domain.usecase.catsDetail

import com.clean.lbg.domain.repositories.CatDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CheckFavouriteUseCaseImpl(private val catDetailsRepo: CatDetailsRepository) :
    CheckFavUseCase {
    override suspend fun execute(imageId: String): Flow<Int?> = flow {
        emit(catDetailsRepo.fetchIsFavouriteRelation(imageId))
    }
}
