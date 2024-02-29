package com.example.candyshop.data

import com.example.candyshop.network.CandyItem

sealed interface CandyUiState {
    data class Success(val items: List<CandyItem>) : CandyUiState
    object Error : CandyUiState
    object Loading : CandyUiState
}