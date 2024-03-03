package com.clean.lbg.data.services.catsDetail

import com.clean.lbg.data.models.SuccessResponse
import com.clean.lbg.data.models.catDetails.PostFavCatModel
import com.clean.lbg.data.services.CatsService
import retrofit2.Response

class CatDetailsApiServiceHelperImpl(private val service: CatsService) :
    CatDetailsApiServiceHelper {
    override suspend fun postFavouriteCat(favCat: PostFavCatModel): Response<SuccessResponse> =
        service.postFavouriteCat(favCat)

    override suspend fun deleteFavouriteCat(favouriteId: Int): Response<SuccessResponse> =
        service.deleteFavouriteCat(favouriteId)
}