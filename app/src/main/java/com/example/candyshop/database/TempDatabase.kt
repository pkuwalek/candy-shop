package com.example.candyshop.database

import com.example.candyshop.network.CandyItem

object TempDatabase {
    val allDesserts: MutableList<CandyItem> = mutableListOf()

    fun findOne(id: Int?): CandyItem? {
        return allDesserts.firstOrNull { id == it.id.toInt() }
    }
}

