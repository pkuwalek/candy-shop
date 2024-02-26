package com.example.candyshop.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.candyshop.data.CartItem
import com.example.candyshop.data.ContentItem
import com.example.candyshop.network.CandyApi
import kotlinx.coroutines.launch

//sealed interface CandyUiState {
//    data class Success(val photos: List<CandyItem>) : CandyUiState
//    object Error : CandyUiState
//    object Loading : CandyUiState
//}


class ShopViewModel : ViewModel() {
    var textFieldInput by mutableStateOf("")
        private set
    var showCart by mutableStateOf(false)
    private val _shoppingCartItems = mutableStateListOf<CartItem>()
    val shoppingCartItems: List<CartItem> = _shoppingCartItems

//    var candyUiState: CandyUiState by mutableStateOf(CandyUiState.Loading)
//        private set
    var candyUiState: String by mutableStateOf("")
        private set

    init {
        getCandyDetails()
    }
    fun updateTextField(userInput: String) {
        textFieldInput = userInput
    }

    private fun getCandyDetails() {
        viewModelScope.launch {
            val listResult = CandyApi.retrofitService.getDesserts()
            candyUiState = listResult
        }
    }

    fun addToCart(candyItem: ContentItem?) {
        if (textFieldInput != "") {
            val item = candyItem?.let {
                CartItem(candyId = candyItem.id,
                    candyName = candyItem.name,
                    candyPrice = it.price,
                    candyQuantity = textFieldInput.toInt())
            }
            if (item != null) {
                _shoppingCartItems.add(item)
            }
        }
    }
}