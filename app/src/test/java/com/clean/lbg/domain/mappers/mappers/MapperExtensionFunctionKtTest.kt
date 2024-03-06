package com.clean.lbg.domain.mappers.mappers

import com.clean.lbg.data.models.SuccessResponse
import com.clean.lbg.data.models.catData.CatResponse
import com.clean.lbg.data.models.catData.FavouriteCatsItem
import com.clean.lbg.domain.mappers.mapCatsDataItems
import com.clean.lbg.domain.mappers.mapFavCatsDataItems
import com.clean.lbg.domain.mappers.mapSuccessData
import com.clean.lbg.models.catMocks.MockFavouriteCatsResponse
import com.clean.lbg.models.catMocks.MockSuccessResponse
import com.clean.lbg.models.catMocks.MocksCatsDataModel
import org.junit.Test
import kotlin.test.assertEquals

class MapperExtensionFunctionKtTest {

    @Test
    fun mapCatsDataItems() {
        val catResponse = CatResponse(MocksCatsDataModel().breeds, 23, "img1", "www.image.com", 200)
        val finalResult = catResponse.mapCatsDataItems()
        assertEquals(catResponse.id, finalResult.imageId)
    }

    @Test
    fun mapFavCatsDataItems() {
        val mocksFavCatsDataModel = MockFavouriteCatsResponse()
        val favCatResponse = FavouriteCatsItem(
            mocksFavCatsDataModel.createdAt,
            mocksFavCatsDataModel.id,
            mocksFavCatsDataModel.image,
            mocksFavCatsDataModel.imageId,
            mocksFavCatsDataModel.subId,
            mocksFavCatsDataModel.userId
        )
        val finalResult = favCatResponse.mapFavCatsDataItems()
        assertEquals(favCatResponse.imageId, finalResult.imageId)
        assertEquals(favCatResponse.image.url, finalResult.url)
    }

    @Test
    fun mapSuccessData() {
        val mockSuccessResponse = MockSuccessResponse()
        val successResponse = SuccessResponse(mockSuccessResponse.id, mockSuccessResponse.message)
        val finalResult = successResponse.mapSuccessData()
        assertEquals(successResponse.id, finalResult.id)
        assertEquals(successResponse.message, finalResult.successMessage)

    }
}