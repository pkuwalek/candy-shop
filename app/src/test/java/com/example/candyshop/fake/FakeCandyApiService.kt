package com.example.candyshop.fake

import com.example.candyshop.api.CandyApiService
import com.example.candyshop.api.CandyListDto

class FakeCandyApiService : CandyApiService {
    override suspend fun getDesserts(): CandyListDto {
        return FakeDataSource.dessertList
    }
}