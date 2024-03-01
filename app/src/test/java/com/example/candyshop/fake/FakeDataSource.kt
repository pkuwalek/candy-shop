package com.example.candyshop.fake

import com.example.candyshop.network.CandyItem
import com.example.candyshop.network.Meals

object FakeDataSource {
    val dessertList = Meals(
        meals = listOf(
            CandyItem(
                name = "FirstDessert",
                imageUrl = "url1",
                id = "12345",
                price = 10
            ), CandyItem(
                name = "SecondDessert",
                imageUrl = "url2",
                id = "54321",
                price = 10
            )
        )
    )
}
