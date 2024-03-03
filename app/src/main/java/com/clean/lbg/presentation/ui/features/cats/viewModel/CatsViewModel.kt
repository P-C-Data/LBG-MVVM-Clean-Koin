package com.clean.lbg.presentation.ui.features.cats.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clean.lbg.data.NetworkResult
import com.clean.lbg.domain.usecase.cats.GetCatsUseCase
import com.clean.lbg.domain.usecase.cats.GetFavCatsUseCase
import com.clean.lbg.presentation.contracts.BaseContract
import com.clean.lbg.presentation.contracts.CatContract
import com.clean.lbg.utils.ErrorsMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatsViewModel(
    private val catUseCase: GetCatsUseCase,
    private val getFavCatsUseCase: GetFavCatsUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    init {
        getCatsData()
        getFavCatsData()
    }

    private val _state = MutableStateFlow(
        CatContract.State(
            cats = listOf(),
            favCatsList = listOf(),
            isLoading = true
        )
    )
    val state: StateFlow<CatContract.State> = _state

    var effects = Channel<BaseContract.Effect>(Channel.UNLIMITED)
        private set

    private fun updateState(newState: CatContract.State) {
        _state.value = newState
    }

    fun getFavCatsData() {
        viewModelScope.launch(dispatcher) {
            getFavCatsUseCase.execute().collect {
                when (it) {
                    is NetworkResult.Success -> {
                        val newState = _state.value.copy(favCatsList = it.data!!, isLoading = false)
                        updateState(newState)
                        effects.send(BaseContract.Effect.DataWasLoaded)
                    }

                    is NetworkResult.Error -> {
                        val newState = state.value.copy(isLoading = false)
                        updateState(newState)
                        effects.send(
                            BaseContract.Effect.Error(
                                it.message ?: ErrorsMessage.gotApiCallError
                            )
                        )
                    }

                    is NetworkResult.Loading -> {
                        if (!state.value.isLoading) {
                            val newState = state.value.copy(isLoading = true)
                            updateState(newState)
                        }

                    }
                }

            }
        }
    }

    fun getCatsData() {
        viewModelScope.launch(dispatcher) {
            catUseCase.execute().collect {
                when (it) {
                    is NetworkResult.Success -> {
                        val newState = state.value.copy(cats = it.data!!, isLoading = false)
                        updateState(newState)
                        effects.send(BaseContract.Effect.DataWasLoaded)
                    }

                    is NetworkResult.Error -> {
                        val newState = state.value.copy(isLoading = false)
                        updateState(newState)
                        effects.send(
                            BaseContract.Effect.Error(
                                it.message ?: ErrorsMessage.gotApiCallError
                            )
                        )
                    }

                    is NetworkResult.Loading -> {
                        if (!state.value.isLoading) {
                            val newState = state.value.copy(isLoading = true)
                            updateState(newState)
                        }
                    }

                }
            }

        }
    }


}