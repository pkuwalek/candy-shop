package com.example.candyshop

import app.cash.turbine.test
import com.example.candyshop.api.CandyItemsRepository
import com.example.candyshop.data.Resource
import com.example.candyshop.data.model.Candy
import com.example.candyshop.rules.TestDispatcherRule
import com.example.candyshop.ui.CandyShopViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test

class CandyShopViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var candyItemsRepository: CandyItemsRepository

    private lateinit var candyShopViewModel: CandyShopViewModel

    @Test
    fun candyShopViewModel_getDessertDetails_verifyCandyUiStateSuccess() = runTest {
        val fakeCandyList = listOf(
            Candy(
                name = "FirstDessert",
                imageUrl = "url1",
                id = "12345",
                price = 10
            ), Candy(
                name = "SecondDessert",
                imageUrl = "url2",
                id = "54321",
                price = 10
            )
        )

        coEvery {
            candyItemsRepository.getCandyItems(false)
        } returns flowOf(Resource.Success(data = fakeCandyList))
        candyShopViewModel = CandyShopViewModel(candyItemsRepository)

        flowOf(candyShopViewModel.candyUiState.value.dessertList).test {
            assertEquals(fakeCandyList, awaitItem())
            awaitComplete()
        }
        flowOf(candyShopViewModel.candyUiState.value.isLoading).test {
            assertFalse(awaitItem())
            awaitComplete()
        }
        coVerify {
            candyItemsRepository
            candyItemsRepository.getCandyItems(false)
            candyShopViewModel.candyUiState.value.isLoading
            candyShopViewModel.candyUiState.value.dessertList
        }
    }

    @Test
    fun candyShopViewModel_getDessertDetails_verifyCandyUiStateError() = runTest {
        coEvery {
            candyItemsRepository.getCandyItems(false)
        } returns flowOf(Resource.Error(message = "Error loading desserts. IOException"))
        candyShopViewModel = CandyShopViewModel(candyItemsRepository)

        flowOf(candyShopViewModel.candyUiState.value.isLoading).test {
            assertFalse(awaitItem())
            awaitComplete()
        }
        coVerify {
            candyItemsRepository
            candyItemsRepository.getCandyItems(false)
            candyShopViewModel.candyUiState.value.isLoading
        }
    }
}