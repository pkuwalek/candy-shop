package com.example.candyshop

import com.example.candyshop.data.CandyUiState
import com.example.candyshop.fake.FakeDataSource
import com.example.candyshop.fake.FakeNetworkCandyItemsRepository
import com.example.candyshop.rules.TestDispatcherRule
import com.example.candyshop.ui.CandyShopViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class CandyShopViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun candyShopViewModel_getCandyItems_verifyCandyUiStateSuccess() =
        runTest {
            val candyShopViewModel = CandyShopViewModel(
                candyItemsRepository = FakeNetworkCandyItemsRepository()
            )
            assertEquals(
                CandyUiState.Success(FakeDataSource.dessertList.meals),
                candyShopViewModel.candyUiState
            )
        }
}