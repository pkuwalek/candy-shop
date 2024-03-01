package com.example.candyshop.fake

import com.example.candyshop.network.CandyApiService
import com.example.candyshop.network.Meals

class FakeCandyApiService : CandyApiService {
    override suspend fun getDesserts(): Meals {
        return FakeDataSource.dessertList
    }
}