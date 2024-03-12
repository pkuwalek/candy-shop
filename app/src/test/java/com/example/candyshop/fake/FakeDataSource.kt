package com.example.candyshop.fake

import com.example.candyshop.api.CandyDto
import com.example.candyshop.api.CandyListDto

object FakeDataSource {
    val dessertList = CandyListDto(
        meals = listOf(
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
        )
    )
}
