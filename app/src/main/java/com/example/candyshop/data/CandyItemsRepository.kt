package com.example.candyshop.data

import com.example.candyshop.network.CandyApiService
import com.example.candyshop.network.CandyItem
import com.example.candyshop.network.Meals

// here q : should be meals?
interface CandyItemsRepository {
    suspend fun getCandyItems() : Meals
}

class NetworkCandyItemsRepository(
    private val candyApiService: CandyApiService
) : CandyItemsRepository {
    override suspend fun getCandyItems(): Meals = candyApiService.getDesserts()
}