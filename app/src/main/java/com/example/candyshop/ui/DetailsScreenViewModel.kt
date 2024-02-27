package com.example.candyshop.ui

//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.candyshop.data.CandyItemsRepository
//import com.example.candyshop.network.Meals
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.launch
//import java.io.IOException

//class DetailsScreenViewModel(private val candyItemsRepository: CandyItemsRepository) : ViewModel() {
//    var textFieldInput by mutableStateOf("")
//        private set
//    var showCart by mutableStateOf(false)
//    private val _shoppingCartItems = mutableStateListOf<CartItem>()
//    val shoppingCartItems: List<CartItem> = _shoppingCartItems

//    private val _desserts = MutableStateFlow<Meals?>(null)
//    val desserts = _desserts.asStateFlow()
//
//    private val _chosenDessert = MutableStateFlow<Meals?>(null)
//    val chosenDessert = _chosenDessert.asStateFlow()
//
//    fun updateTextField(userInput: String) {
//        textFieldInput = userInput
//    }

//    fun getDessertById(dessertId: Int) {
//        return (_desserts.value as CandyUiState.Success).find {
//            it.id == dessertId
//        }
//    }
//        return candyItemsRepository.getCandyItems().meals.firstOrNull { candyId == it.id.toInt() }

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
//}