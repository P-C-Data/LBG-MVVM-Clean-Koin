package com.clean.lbg.presentation.ui.features.catDetails.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clean.lbg.data.NetworkResult
import com.clean.lbg.domain.usecase.catsDetail.CheckFavUseCase
import com.clean.lbg.domain.usecase.catsDetail.DeleteFavCatUseCase
import com.clean.lbg.domain.usecase.catsDetail.PostFavCatUseCase
import com.clean.lbg.presentation.contracts.BaseContract
import com.clean.lbg.presentation.contracts.CatDetailsContract
import com.clean.lbg.utils.ErrorsMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatsDetailsViewModel(
    private val postFavCatUseCase: PostFavCatUseCase,
    private val deleteFavCatUseCase: DeleteFavCatUseCase,
    private val checkFavouriteUseCase: CheckFavUseCase,
    private val dispatcher: CoroutineDispatcher

) : ViewModel() {

    var state by mutableStateOf(
        CatDetailsContract.State(
            postFavCatSuccess = null,
            deleteFavCatSuccess = null,
        )
    )
        private set

    private val _isFavourite = MutableStateFlow(false)
    var isFavourite = _isFavourite.asStateFlow()
    fun updateFavouriteState(newValue: Boolean) {
        viewModelScope.launch(dispatcher) {
            _isFavourite.value = newValue
        }
    }

    private lateinit var imageId: String
    var favId: Int = 0

    var effects = Channel<BaseContract.Effect>(Channel.UNLIMITED)
        private set

    fun checkFav(imageId: String) {
        this.imageId = imageId
        viewModelScope.launch(dispatcher) {
            checkFavouriteUseCase.execute(imageId).collect {
                _isFavourite.value = it != null && it != 0
                if (it != null) {
                    favId = it
                }
            }
        }


    }


    fun postFavCatData() {
        viewModelScope.launch(dispatcher) {
            postFavCatUseCase.execute(imageId).collect {
                when (it) {
                    is NetworkResult.Success -> {
                        favId = it.data?.id ?: favId
                        state = state.copy(postFavCatSuccess = it.data!!)
                        _isFavourite.value = true
                        effects.send(BaseContract.Effect.DataWasLoaded)
                    }

                    is NetworkResult.Error -> {
                        effects.send(
                            BaseContract.Effect.Error(
                                it.message ?: ErrorsMessage.gotApiCallError
                            )
                        )
                    }

                    else -> {}
                }
            }

        }
    }

    fun deleteFavCatData() {
        if (isFavourite.value)
            viewModelScope.launch(dispatcher) {
                deleteFavCatUseCase.execute(imageId, favId).collect {
                    when (it) {
                        is NetworkResult.Success -> {
                            state = state.copy(deleteFavCatSuccess = it.data!!)
                            _isFavourite.value = false
                            effects.send(BaseContract.Effect.DataWasLoaded)
                        }

                        is NetworkResult.Error -> {
                            effects.send(
                                BaseContract.Effect.Error(
                                    it.message ?: ErrorsMessage.gotApiCallError
                                )
                            )
                        }

                        else -> {}
                    }
                }

            }
    }


}