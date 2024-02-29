package com.example.candyshop.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.candyshop.CandyApplication
import com.example.candyshop.database.TempDatabase
import com.example.candyshop.network.CandyItem

class DetailsScreenViewModel(private val temporaryDb: TempDatabase) : ViewModel() {
    var textFieldInput by mutableStateOf("")
        private set
    var showCart by mutableStateOf(false)

//    private val _shoppingCartItems = mutableStateListOf<CartItem>()
//    val shoppingCartItems: List<CartItem> = _shoppingCartItems

    fun updateTextField(userInput: String) {
        textFieldInput = userInput
    }

    fun getDessertById(id: Int?): CandyItem? {
        return temporaryDb.findOne(id)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                (this[APPLICATION_KEY] as CandyApplication)
                val temporaryDb = TempDatabase
                DetailsScreenViewModel(temporaryDb = temporaryDb)
            }
        }
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