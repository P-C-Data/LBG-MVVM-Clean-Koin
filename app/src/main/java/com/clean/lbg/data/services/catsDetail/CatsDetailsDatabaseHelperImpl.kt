package com.clean.lbg.data.services.catsDetail

import com.clean.lbg.data.database.LBGDatabase
import com.clean.lbg.data.database.entities.FavImageEntity

class CatsDetailsDatabaseHelperImpl(private val db: LBGDatabase) : CatsDetailsDatabaseHelper {
    override suspend fun insertFavCatImageRelation(favCatId: Int, imageId: String): Long {
        return FavImageEntity(favCatId, imageId).let {
            db.favImageDao().insertFavCatImageRelation(it)
        }
    }

    override suspend fun deleteFavImage(catImageId: String): Int {
        return db.favImageDao().deleteFavImage(catImageId)
    }

    override suspend fun isFavourite(catImageId: String): Int? {
        return db.favImageDao().getFavId(catImageId)
    }
}