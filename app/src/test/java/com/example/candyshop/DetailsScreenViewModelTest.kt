package com.example.candyshop

import androidx.lifecycle.SavedStateHandle
import com.example.candyshop.api.CandyItemsRepository
import com.example.candyshop.data.model.Candy
import com.example.candyshop.rules.TestDispatcherRule
import com.example.candyshop.ui.DetailsScreenViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailsScreenViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val mainDispatcherRule = TestDispatcherRule()

    @MockK
    private var savedStateHandle = SavedStateHandle()

    @MockK
    private lateinit var candyItemsRepository: CandyItemsRepository

    private lateinit var detailsScreenViewModel: DetailsScreenViewModel

    @Before
    fun setUp() {
        every { savedStateHandle.get<Int>("id") } returns 12345
        coEvery { candyItemsRepository.getCandy(12345) } returns null
        detailsScreenViewModel = DetailsScreenViewModel(candyItemsRepository, savedStateHandle)
    }

    @Test
    fun detailsScreenViewModel_detailsStateTextFieldInput_verifyStartingValue() {
        assertEquals("", detailsScreenViewModel.detailsState.value.textFieldInput)
        verify {
            candyItemsRepository
            savedStateHandle.get<Int>("id")
            detailsScreenViewModel.detailsState.value.textFieldInput
        }
    }

    @Test
    fun detailsScreenViewModel_updateTextField_verifyTextFieldInput() {
        val sampleString = "candy shop"
        detailsScreenViewModel.updateTextField(sampleString)

        assertEquals(sampleString, detailsScreenViewModel.detailsState.value.textFieldInput)
        verify {
            candyItemsRepository
            savedStateHandle.get<Int>("id")
            detailsScreenViewModel.detailsState.value.textFieldInput
        }
    }

    @Test
    fun detailsScreenViewModel_updateShowCart_verifyStartingValue() {
        assertEquals(false, detailsScreenViewModel.detailsState.value.showCart)
        verify {
            candyItemsRepository
            savedStateHandle.get<Int>("id")
            detailsScreenViewModel.detailsState.value.showCart
        }
    }

    @Test
    fun detailsScreenViewModel_updateShowCart_verifyUpdateValue() {
        detailsScreenViewModel.updateShowCart(true)

        assertEquals(true, detailsScreenViewModel.detailsState.value.showCart)
        verify {
            candyItemsRepository
            savedStateHandle.get<Int>("id")
            detailsScreenViewModel.detailsState.value.showCart
        }
    }

    @Test
    fun detailsScreenViewModel_getDessertById_verifyResultSuccess() = runTest {
        val result = Candy(
            name = "FirstDessert",
            imageUrl = "url1",
            id = "12345",
            price = 10
        )
        coEvery { candyItemsRepository.getCandy(12345) } returns result
        detailsScreenViewModel = DetailsScreenViewModel(candyItemsRepository, savedStateHandle)

        assertEquals(result, detailsScreenViewModel.detailsState.value.dessert)
        assertFalse(detailsScreenViewModel.detailsState.value.isLoading)
        coVerify {
            candyItemsRepository
            savedStateHandle.get<Int>("id")
            candyItemsRepository.getCandy(12345)
            detailsScreenViewModel.detailsState.value.isLoading
            detailsScreenViewModel.detailsState.value.dessert
        }
    }

    @Test
    fun detailsScreenViewModel_getDessertById_verifyResultNotFound() = runTest {
        val sampleId = -1
        every { savedStateHandle.get<Int>("id") } returns -1
        coEvery { candyItemsRepository.getCandy(sampleId) } returns null
        detailsScreenViewModel = DetailsScreenViewModel(candyItemsRepository, savedStateHandle)

        assertTrue(detailsScreenViewModel.detailsState.value.isLoading)
        assertNull(detailsScreenViewModel.detailsState.value.dessert)
        coVerify {
            candyItemsRepository
            savedStateHandle.get<Int>("id")
            candyItemsRepository.getCandy(-1)
            detailsScreenViewModel.detailsState.value.isLoading
            detailsScreenViewModel.detailsState.value.dessert
        }
    }
}