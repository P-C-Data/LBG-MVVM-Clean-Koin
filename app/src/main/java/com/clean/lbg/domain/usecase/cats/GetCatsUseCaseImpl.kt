package com.clean.lbg.domain.usecase.cats

import com.clean.lbg.data.NetworkResult
import com.clean.lbg.domain.mappers.mapCatsDataItems
import com.clean.lbg.domain.mappers.models.CatDataModel
import com.clean.lbg.domain.repositories.CatsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetCatsUseCaseImpl(private val catsRepo: CatsRepository) : GetCatsUseCase {
    override suspend fun execute() = flow<NetworkResult<List<CatDataModel>>> {
        emit(NetworkResult.Loading())
        with(catsRepo.fetchCats()) {
            if (isSuccessful) {
                val catDataList = this.body()?.map { cat ->
                    cat.mapCatsDataItems()
                }
                emit(NetworkResult.Success(catDataList))
            } else {
                emit(NetworkResult.Error(this.errorBody().toString()))
            }
        }
    }.catch {
        emit(NetworkResult.Error(it.localizedMessage))
    }
}

