package com.clean.lbg.data.services.catsDetail

import com.clean.lbg.data.models.SuccessResponse
import com.clean.lbg.data.models.catDetails.PostFavCatModel
import retrofit2.Response

interface CatDetailsApiServiceHelper {
    suspend fun postFavouriteCat(favCat: PostFavCatModel): Response<SuccessResponse>
    suspend fun deleteFavouriteCat(favouriteId: Int): Response<SuccessResponse>

}