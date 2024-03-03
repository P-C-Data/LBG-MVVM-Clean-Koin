package com.clean.lbg.modelsMocks

import com.clean.lbg.data.models.catData.BreedWeight


data class MockWeight(
    val imperial: String = "23",
    val metric: String = "25"
)

fun toResponseCatBreedWeight(mockWeight: MockWeight): BreedWeight {
    return BreedWeight(mockWeight.imperial, mockWeight.metric)
}