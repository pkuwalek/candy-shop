package com.example.candyshop.data

import com.example.candyshop.network.CandyApiService
import com.example.candyshop.network.Meals

// api helper
interface CandyItemsRepository {
    suspend fun getCandyItems() : Meals
}

// api helper implementation
class NetworkCandyItemsRepository(
    private val candyApiService: CandyApiService
) : CandyItemsRepository {
    override suspend fun getCandyItems(): Meals = candyApiService.getDesserts()
}