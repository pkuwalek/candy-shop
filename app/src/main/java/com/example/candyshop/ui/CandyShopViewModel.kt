package com.example.candyshop.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.candyshop.CandyApplication
import com.example.candyshop.data.CandyItemsRepository
import com.example.candyshop.data.CandyUiState
import com.example.candyshop.database.TempDatabase
import kotlinx.coroutines.launch
import java.io.IOException

class CandyShopViewModel(private val candyItemsRepository: CandyItemsRepository) : ViewModel() {
    var candyUiState: CandyUiState by mutableStateOf(CandyUiState.Loading)
        private set

    init {
        getDessertDetails()
    }

    private fun getDessertDetails() {
        viewModelScope.launch {
            try {
                val listResult = candyItemsRepository.getCandyItems()
                candyUiState = CandyUiState.Success(listResult.meals)
                TempDatabase.allDesserts.addAll(listResult.meals)
            } catch (e: IOException) {
                CandyUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CandyApplication)
                val candyItemsRepository = application.container.candyItemsRepository
                CandyShopViewModel(candyItemsRepository = candyItemsRepository)
            }
        }
    }
}