package com.example.candyshop.data

import app.cash.turbine.test
import com.example.candyshop.api.CandyApiService
import com.example.candyshop.api.CandyDto
import com.example.candyshop.api.CandyItemsRepositoryImplementation
import com.example.candyshop.api.CandyListDto
import com.example.candyshop.data.db.CandyDatabase
import com.example.candyshop.data.db.CandyEntity
import com.example.candyshop.data.model.Candy
import com.example.candyshop.rules.TestDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class CandyItemsRepositoryImplementationTest {
    private val result = listOf(
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
    private val fakeLocalCandyList = listOf(
        CandyEntity(
            name = "FirstDessert",
            imageUrl = "url1",
            id = "12345",
            price = 10
        ), CandyEntity(
            name = "SecondDessert",
            imageUrl = "url2",
            id = "54321",
            price = 10
        )
    )
    private val fakeRemoteCandyList = CandyListDto(meals = listOf (
        CandyDto(
            name = "FirstDessert",
            imageUrl = "url1",
            id = "12345",
            price = 10
        ), CandyDto(
            name = "SecondDessert",
            imageUrl = "url2",
            id = "54321",
            price = 10
        )
    ))

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    private lateinit var candyApiService: CandyApiService

    @RelaxedMockK
    private lateinit var candyDatabase: CandyDatabase

    private lateinit var candyItemsRepositoryImplementation: CandyItemsRepositoryImplementation

    @Test
    fun candyItemsRepositoryImplementation_getCandyItems_loadLocallySuccess() = runTest {
        coEvery { candyDatabase.candyDao.getAllCandy() } returns fakeLocalCandyList
        candyItemsRepositoryImplementation = CandyItemsRepositoryImplementation(candyApiService, candyDatabase)
        candyItemsRepositoryImplementation.getCandyItems(forceFetchFromRemote = false).test {
            assertEquals(result, awaitItem().data)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun candyItemsRepositoryImplementation_getCandyItems_loadFromRemoteSuccess() = runTest {
        coEvery { candyApiService.getDesserts() } returns fakeRemoteCandyList
        candyItemsRepositoryImplementation = CandyItemsRepositoryImplementation(candyApiService, candyDatabase)
        candyItemsRepositoryImplementation.getCandyItems(forceFetchFromRemote = true).test {
            assertEquals(result, awaitItem().data)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test(expected = IOException::class)
    fun candyItemsRepositoryImplementation_getCandyItems_localCandyListNotFound() = runTest {
        coEvery { candyDatabase.candyDao.getAllCandy() }.throws(IOException())
        candyItemsRepositoryImplementation = CandyItemsRepositoryImplementation(candyApiService, candyDatabase)
        val flow = candyItemsRepositoryImplementation.getCandyItems(true)
        flow.collect { resource: Resource<List<Candy>> ->
            assertEquals(null, resource.data)
        }
    }

    @Test
    fun candyItemsRepositoryImplementation_getCandyItems_loadFromRemoteIoException() = runTest {
        coEvery { candyApiService.getDesserts() }.throws(IOException())
        candyItemsRepositoryImplementation = CandyItemsRepositoryImplementation(candyApiService, candyDatabase)
        candyItemsRepositoryImplementation.getCandyItems(true).test {
            assertEquals("Error loading desserts. IOException", awaitItem().message)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun candyItemsRepositoryImplementation_getCandyItems_loadFromRemoteHttpException() = runTest {
        coEvery { candyApiService.getDesserts() }.throws(HttpException(Response.error<ResponseBody>(500 ,
            "HTTP Exception".toResponseBody("plain/text".toMediaTypeOrNull())
        )))
        candyItemsRepositoryImplementation = CandyItemsRepositoryImplementation(candyApiService, candyDatabase)
        candyItemsRepositoryImplementation.getCandyItems(true).test {
            assertEquals("Error loading desserts. HTTPException", awaitItem().message)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun candyItemsRepositoryImplementation_getCandyItems_loadFromRemoteException() = runTest {
        coEvery { candyApiService.getDesserts() }.throws(Exception())
        candyItemsRepositoryImplementation = CandyItemsRepositoryImplementation(candyApiService, candyDatabase)
        candyItemsRepositoryImplementation.getCandyItems(true).test {
            assertEquals("Error loading desserts. GeneralException", awaitItem().message)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun candyItemsRepositoryImplementation_getCandyItems_upsertToDbException() = runTest {
        coEvery { candyApiService.getDesserts() } returns fakeRemoteCandyList
        coEvery { candyDatabase.candyDao.upsertCandyList(fakeLocalCandyList) }.throws(Exception())
        candyItemsRepositoryImplementation = CandyItemsRepositoryImplementation(candyApiService, candyDatabase)
        candyItemsRepositoryImplementation.getCandyItems(true).test {
            assertEquals("Error updating the database.", awaitItem().message)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun candyItemsRepositoryImplementation_getCandy_dessertFound() = runTest {
        val resultFromDb = CandyEntity(
            name = "FirstDessert",
            imageUrl = "url1",
            id = "12345",
            price = 10
        )
        val expectedResult = Candy(
            name = "FirstDessert",
            imageUrl = "url1",
            id = "12345",
            price = 10
        )
        coEvery { candyDatabase.candyDao.getCandyById(12345) } returns resultFromDb
        candyItemsRepositoryImplementation = CandyItemsRepositoryImplementation(candyApiService, candyDatabase)
        assertEquals(expectedResult, candyItemsRepositoryImplementation.getCandy(12345))
    }

    @Test
    fun candyItemsRepositoryImplementation_getCandy_dessertNotFound() = runTest {
        coEvery { candyDatabase.candyDao.getCandyById(123) } returns null
        candyItemsRepositoryImplementation = CandyItemsRepositoryImplementation(candyApiService, candyDatabase)
        assertNull(candyItemsRepositoryImplementation.getCandy(123))
    }
}