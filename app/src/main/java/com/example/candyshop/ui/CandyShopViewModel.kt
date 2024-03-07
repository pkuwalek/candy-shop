package com.example.candyshop.ui

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
class CandyShopViewModel @Inject constructor(
    private val candyItemsRepository: CandyItemsRepository
) : ViewModel() {
    private var _candyUiState = MutableStateFlow(CandyUiState())
    val candyUiState = _candyUiState.asStateFlow()

    init {
        getDessertDetails(false)
    }

    private fun getDessertDetails(forceFetchFromRemote: Boolean) {
        viewModelScope.launch {
            _candyUiState.update {
                it.copy(isLoading = true)
            }
            candyItemsRepository.getCandyItems(forceFetchFromRemote).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _candyUiState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Success -> {
                        result.data?.let { candyList ->
                            _candyUiState.update {
                                it.copy(
                                    isLoading = false,
                                    dessertList = candyUiState.value.dessertList + candyList
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}