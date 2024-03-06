package com.example.candyshop.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CandyDao {
    @Upsert
    suspend fun upsertCandyList(candyList: List<CandyEntity>)

    @Query("SELECT * FROM CandyEntity")
    suspend fun getAllCandy() : List<CandyEntity>

    @Query("SELECT * FROM CandyEntity WHERE id = :id")
    suspend fun getCandyById(id: Int) : CandyEntity
}