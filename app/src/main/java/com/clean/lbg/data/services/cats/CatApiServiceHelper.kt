package com.clean.lbg.data.services.cats

import com.clean.lbg.data.models.catData.CatResponse
import com.clean.lbg.data.models.catData.FavouriteCatsItem
import retrofit2.Response

interface CatApiServiceHelper {
    suspend fun fetchCatsImages(limit: Int): Response<List<CatResponse>>
    suspend fun fetchFavouriteCats(subId: String): Response<List<FavouriteCatsItem>>

}