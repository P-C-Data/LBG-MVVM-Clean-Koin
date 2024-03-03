package com.clean.lbg.data.services.cats

import com.clean.lbg.data.database.LBGDatabase
import com.clean.lbg.data.database.entities.FavImageEntity
import com.clean.lbg.data.models.catData.FavouriteCatsItem

class CatsDatabaseHelperImpl(private val db: LBGDatabase) : CatsDatabaseHelper {
    override suspend fun insertFavCatImageRelation(favCatItems: List<FavouriteCatsItem>): List<Long> {
        val favCatRelList = favCatItems.map {
            FavImageEntity(
                favouriteId = it.id,
                imageId = it.imageId
            )
        }
        return favCatRelList.let { db.favImageDao().insertFavCatImageRelation(favCatRelList) }
    }
}