package com.example.candyshop.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.candyshop.data.CartItem
import com.example.candyshop.data.ContentItem

class ShopViewModel : ViewModel() {
    var textFieldInput by mutableStateOf("")
        private set
    var showCart by mutableStateOf(false)
    private val _shoppingCartItems = mutableStateListOf<CartItem>()
    val shoppingCartItems: List<CartItem> = _shoppingCartItems

    fun updateTextField(userInput: String) {
        textFieldInput = userInput
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