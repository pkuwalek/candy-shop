package com.example.candyshop.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.candyshop.api.CandyItemsRepository
import com.example.candyshop.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val candyItemsRepository: CandyItemsRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var textFieldInput by mutableStateOf("")
        private set
    var showCart by mutableStateOf(false)

//    private val _shoppingCartItems = mutableStateListOf<CartItem>()
//    val shoppingCartItems: List<CartItem> = _shoppingCartItems

    private val candyId = savedStateHandle.get<Int>("id")

    private var _detailsState = MutableStateFlow(DetailsState())
    val detailsState = _detailsState.asStateFlow()

    fun updateTextField(userInput: String) {
        textFieldInput = userInput
    }

    init {
        getDessertById(candyId ?: -1)
    }

    private fun getDessertById(id: Int) {
        viewModelScope.launch {
            _detailsState.update {
                it.copy(isLoading = true)
            }
            candyItemsRepository.getCandy(id).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _detailsState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _detailsState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                    is Resource.Success -> {
                        result.data?.let { candy ->
                            _detailsState.update {
                                it.copy(dessert = candy)
                            }
                        }
                    }
                }
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