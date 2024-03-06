package com.clean.lbg.domain.mappers

import com.clean.lbg.data.models.SuccessResponse
import com.clean.lbg.data.models.catData.CatResponse
import com.clean.lbg.data.models.catData.FavouriteCatsItem
import com.clean.lbg.domain.mappers.models.CallSuccessModel
import com.clean.lbg.domain.mappers.models.CatDataModel

//CatData Mapper function used for Cat image listData at Cats
fun CatResponse.mapCatsDataItems(): CatDataModel {
    return CatDataModel(
        name = this.breeds[0].name,
        origin = this.breeds[0].origin,
        imageId = this.id,
        url = this.url
    )
}

fun FavouriteCatsItem.mapFavCatsDataItems(): CatDataModel {
    return CatDataModel(
        favId = this.id,
        url = this.image.url,
        imageId = this.imageId,
    )
}

fun SuccessResponse.mapSuccessData(): CallSuccessModel {
    return CallSuccessModel(
        successMessage = this.message,
        id = this.id
    )
}



