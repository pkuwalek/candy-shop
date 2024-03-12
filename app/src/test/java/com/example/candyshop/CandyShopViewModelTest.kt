package com.example.candyshop

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.candyshop.data.db.CandyDao
import com.example.candyshop.data.db.CandyDatabase
import com.example.candyshop.data.db.CandyEntity
import com.example.candyshop.fake.FakeCandyItemsRepository
import com.example.candyshop.rules.TestDispatcherRule
import com.example.candyshop.ui.CandyShopViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class CandyShopViewModelTest {

    private val upsertData = listOf(
        CandyEntity(
            name = "FirstDessert",
            imageUrl = "url1",
            id = "12345",
            price = 10
        ), CandyEntity (
            name = "SecondDessert",
            imageUrl = "url2",
            id = "54321",
            price = 10
        )
    )

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var candyShopViewModel: CandyShopViewModel

    @RelaxedMockK
    private lateinit var candyItemsRepository: FakeCandyItemsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        candyShopViewModel = CandyShopViewModel(candyItemsRepository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }


    @Test
    fun candyShopViewModel_getDessertDetails_verifyCandyUiStateDessertList() =
        runTest {


//            assertEquals(
//                CandyUiState.Success(FakeDataSource.dessertList.meals),
//                candyShopViewModel.candyUiState
//            )
        }
}