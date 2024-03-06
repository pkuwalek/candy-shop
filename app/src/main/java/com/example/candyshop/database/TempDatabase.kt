package com.example.candyshop.database

import com.example.candyshop.api.CandyDto

object TempDatabase {
    val allDesserts: MutableList<CandyDto> = mutableListOf()

    fun findOne(id: Int?): CandyDto? {
        return allDesserts.firstOrNull { id == it.id?.toInt() }
    }
}