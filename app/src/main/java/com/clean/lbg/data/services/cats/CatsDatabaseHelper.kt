package com.clean.lbg.data.services.cats

import com.clean.lbg.data.models.catData.FavouriteCatsItem

interface CatsDatabaseHelper {
    suspend fun insertFavCatImageRelation(favCatItems: List<FavouriteCatsItem>): List<Long>
}