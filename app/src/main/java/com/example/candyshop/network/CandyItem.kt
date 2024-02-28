package com.example.candyshop.network

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meals (
    val meals: List<CandyItem>
)

@Serializable
data class CandyItem (
    @SerialName(value = "strMeal")
    val name: String,
    @SerialName(value = "strMealThumb")
    val imageUrl: String,
    @SerialName(value = "idMeal")
    val id: String,
    val price: Int = 10
)