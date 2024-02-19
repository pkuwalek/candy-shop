package com.example.candyshop.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ShopViewModel : ViewModel() {
    var textFieldInput by mutableStateOf("")
        private set
    var showCart by mutableStateOf(false)

    fun updateTextField(userInput: String) {
        textFieldInput = userInput
    }

    fun addToCart() {
        // take textFieldInput
        // check if not empty
        // if not take the number, price and id of candy
        // var cart = candyName, quantity, price(quantity * price)
        // sumCart = all prices added

    }
}