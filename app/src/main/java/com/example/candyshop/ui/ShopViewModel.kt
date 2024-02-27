package com.example.candyshop.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
//import com.example.candyshop.data.CartItem
//import com.example.candyshop.data.ContentItem
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface CandyUiState {
    data class Success(val items: List<CandyItem>) : CandyUiState
    object Error : CandyUiState
    object Loading : CandyUiState
}

class ShopViewModel(private val candyItemsRepository: CandyItemsRepository) : ViewModel() {
    var textFieldInput by mutableStateOf("")
        private set
    var showCart by mutableStateOf(false)
//    private val _shoppingCartItems = mutableStateListOf<CartItem>()
//    val shoppingCartItems: List<CartItem> = _shoppingCartItems

    var candyUiState: CandyUiState by mutableStateOf(CandyUiState.Loading)
        private set

    init {
        getDessertDetails()
    }

    private fun getDessertDetails() {
        viewModelScope.launch {
            candyUiState = try {
                val listResult = candyItemsRepository.getCandyItems()
                CandyUiState.Success(listResult.meals)
            } catch (e: IOException) {
                CandyUiState.Error
            }
        }
    }

    suspend fun getDessertDetailsById(candyId: Int?): CandyItem? {
        return candyItemsRepository.getCandyItems().meals.firstOrNull { candyId == it.id.toInt() }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CandyApplication)
                val candyItemsRepository = application.container.candyItemsRepository
                ShopViewModel(candyItemsRepository = candyItemsRepository)
            }
        }
    }

    fun updateTextField(userInput: String) {
        textFieldInput = userInput
    }

//    fun addToCart(candyItem: ContentItem?) {
//        if (textFieldInput != "") {
//            val item = candyItem?.let {
//                CartItem(candyId = candyItem.id,
//                    candyName = candyItem.name,
//                    candyPrice = it.price,
//                    candyQuantity = textFieldInput.toInt())
//            }
//            if (item != null) {
//                _shoppingCartItems.add(item)
//            }
//        }
//    }
}