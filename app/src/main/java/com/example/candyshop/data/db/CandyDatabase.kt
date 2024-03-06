package com.example.candyshop.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CandyEntity::class],
    version = 1
)
abstract class CandyDatabase: RoomDatabase() {
    abstract val candyDao: CandyDao
}