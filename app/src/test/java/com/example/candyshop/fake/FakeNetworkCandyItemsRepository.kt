package com.example.candyshop.fake

import com.example.candyshop.data.CandyItemsRepository
import com.example.candyshop.network.Meals

class FakeNetworkCandyItemsRepository : CandyItemsRepository {
    override suspend fun getCandyItems(): Meals {
        return FakeDataSource.dessertList
    }
}