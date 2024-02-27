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
import com.example.candyshop.network.CandyItem
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface CandyUiState {
    data class Success(val items: List<CandyItem>) : CandyUiState
    object Error : CandyUiState
    object Loading : CandyUiState
}

class CandyShopViewModel(private val candyItemsRepository: CandyItemsRepository) : ViewModel() {
    var textFieldInput by mutableStateOf("")
        private set
    var showCart by mutableStateOf(false)
//    private val _shoppingCartItems = mutableStateListOf<CartItem>()
//    val shoppingCartItems: List<CartItem> = _shoppingCartItems
    var candyUiState: CandyUiState by mutableStateOf(CandyUiState.Loading)
        private set

    var allDesserts: List<CandyItem> = listOf()

    fun updateTextField(userInput: String) {
        textFieldInput = userInput
    }

    init {
        getDessertDetails()
    }

    private fun getDessertDetails() {
        viewModelScope.launch {
            try {
                val listResult = candyItemsRepository.getCandyItems()
                candyUiState =CandyUiState.Success(listResult.meals)
                allDesserts = listResult.meals
            } catch (e: IOException) {
                CandyUiState.Error
            }

        }
    }

    fun getDessertById(dessertId: Int): CandyItem? = allDesserts.firstOrNull { dessertId == it.id.toInt() }

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