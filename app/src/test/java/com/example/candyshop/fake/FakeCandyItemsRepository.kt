package com.example.candyshop.fake

import com.example.candyshop.api.CandyItemsRepository
import com.example.candyshop.data.Resource
import com.example.candyshop.data.model.Candy
import kotlinx.coroutines.flow.Flow

class FakeCandyItemsRepository(): CandyItemsRepository {

    override suspend fun getCandyItems(forceFetchFromRemote: Boolean): Flow<Resource<List<Candy>>> {
        return getCandyItems(forceFetchFromRemote)
    }
    override suspend fun getCandy(id: Int): Candy? {
        return getCandy(id)
    }
}