package com.example.candyshop.ui

import com.example.candyshop.data.model.Candy

data class CandyUiState (
    val isLoading: Boolean = false,
    val dessertList: List<Candy> = emptyList()
)