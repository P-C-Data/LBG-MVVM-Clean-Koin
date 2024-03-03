package com.clean.lbg.data.repositories

import com.clean.lbg.data.models.catDetails.PostFavCatModel
import com.clean.lbg.data.services.catsDetail.CatDetailsApiServiceHelper
import com.clean.lbg.data.services.catsDetail.CatsDetailsDatabaseHelper
import com.clean.lbg.domain.repositories.CatDetailsRepository


class CatDetailsRepositoryImpl(
    private val catDetailsApiService: CatDetailsApiServiceHelper,
    private val catsDetailsDatabaseHelper: CatsDetailsDatabaseHelper
) : CatDetailsRepository {

    override suspend fun postFavouriteCat(favCat: PostFavCatModel) =
        catDetailsApiService.postFavouriteCat(favCat)

    override suspend fun insertFavouriteCat(favCatId: Int, catImgId: String): Long =
        catsDetailsDatabaseHelper.insertFavCatImageRelation(
            favCatId, catImgId
        )

    override suspend fun deleteFavouriteCatApi(favouriteId: Int) =
        catDetailsApiService.deleteFavouriteCat(favouriteId)

    override suspend fun deleteFavouriteCatLocal(imgId: String): Int =
        catsDetailsDatabaseHelper.deleteFavImage(imgId)


    override suspend fun fetchIsFavouriteRelation(imageId: String) =
        catsDetailsDatabaseHelper.isFavourite(imageId)


}



