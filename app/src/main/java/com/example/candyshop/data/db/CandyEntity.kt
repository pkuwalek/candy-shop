package com.example.candyshop.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

// tables representation
@Entity
data class CandyEntity (
    val name: String,
    val imageUrl: String,
    val price: Int = 10,

    @PrimaryKey
    val id: String,
)