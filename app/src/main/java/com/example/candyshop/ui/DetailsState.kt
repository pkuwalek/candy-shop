package com.example.candyshop.ui

import com.example.candyshop.data.model.Candy

data class DetailsState (
    val isLoading: Boolean = false,
    val dessert: Candy? = null
)