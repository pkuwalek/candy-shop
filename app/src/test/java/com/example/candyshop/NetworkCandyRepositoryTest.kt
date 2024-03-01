package com.example.candyshop

import com.example.candyshop.data.NetworkCandyItemsRepository
import com.example.candyshop.fake.FakeCandyApiService
import com.example.candyshop.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkCandyRepositoryTest {
    @Test
    fun networkCandyItemsRepository_getDesserts_verifyDessertList() =
        runTest {
            val repository = NetworkCandyItemsRepository(
                candyApiService = FakeCandyApiService()
            )
            assertEquals(FakeDataSource.dessertList, repository.getCandyItems())
        }

}