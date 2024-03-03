package com.clean.lbg.presentation.contracts

import com.clean.lbg.domain.test.CatDataModel

class CatContract {
    data class State(
        val cats: List<CatDataModel> = listOf(),
        val favCatsList: List<CatDataModel> = listOf(),
        val isLoading: Boolean = false
    )
}