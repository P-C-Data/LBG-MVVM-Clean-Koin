package com.clean.lbg.domain.mappers.models

data class CatDataModel(
    val name: String? = "",
    val origin: String? = "",
    val favId: Int = 0,
    val imageId: String,
    val url: String,
)
