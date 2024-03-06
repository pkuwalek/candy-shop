package com.example.candyshop.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CandyListDto (
    val meals: List<CandyDto>
)

@Serializable
data class CandyDto (
    @SerialName(value = "strMeal")
    val name: String?,
    @SerialName(value = "strMealThumb")
    val imageUrl: String?,
    @SerialName(value = "idMeal")
    val id: String?,
    val price: Int = 10
)