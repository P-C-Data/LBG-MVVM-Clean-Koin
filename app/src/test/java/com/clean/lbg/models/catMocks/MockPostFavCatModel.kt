package com.clean.lbg.models.catMocks

import com.clean.lbg.data.models.catDetails.PostFavCatModel
import com.clean.lbg.utils.Constants
import com.google.gson.annotations.SerializedName

data class MockPostFavCatModel(
    @SerializedName("image_id")
    val imageId: String = "mi5",
    @SerializedName("sub_id")
    val subId: String = Constants.SUB_ID
)

fun toRequestPostFavCatData(mockPostFavCatModel: MockPostFavCatModel): PostFavCatModel {
    return PostFavCatModel(mockPostFavCatModel.imageId, mockPostFavCatModel.subId)
}