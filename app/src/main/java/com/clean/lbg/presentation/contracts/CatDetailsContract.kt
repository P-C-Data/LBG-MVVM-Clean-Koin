package com.clean.lbg.presentation.contracts


import com.clean.lbg.domain.test.CallSuccessModel


class CatDetailsContract {
    data class State(
        val postFavCatSuccess: CallSuccessModel?,
        val deleteFavCatSuccess: CallSuccessModel?
    )

}