package com.example.candyshop

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.candyshop.data.db.CandyDao
import com.example.candyshop.data.db.CandyDatabase
import com.example.candyshop.data.db.CandyEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CandyDaoTest {
    private lateinit var candyDao: CandyDao
    private lateinit var candyDatabase: CandyDatabase

    private val upsertData = listOf(
        CandyEntity(
            name = "FirstDessert",
            imageUrl = "url1",
            id = "12345",
            price = 10
        ), CandyEntity (
            name = "SecondDessert",
            imageUrl = "url2",
            id = "54321",
            price = 10
        )
    )

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        candyDatabase = Room.inMemoryDatabaseBuilder(context, CandyDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        candyDao = candyDatabase.candyDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        candyDatabase.close()
    }

    private suspend fun addDataToDb() {
        candyDao.upsertCandyList(upsertData)
    }

    @Test
    @Throws(Exception::class)
    fun candyDaoUpsertCandyList_returnsAllItemsFromDb() = runBlocking {
        addDataToDb()
        val candyFromDb = candyDao.getAllCandy()
        assertEquals(upsertData, candyFromDb)
    }

    @Test
    @Throws(Exception::class)
    fun candyDaoGetCandyById_findsOneItemInDb() = runBlocking {
        addDataToDb()
        val oneItem = candyDao.getCandyById(12345)
        assertEquals(upsertData[0], oneItem)
    }

    @Test
    @Throws(Exception::class)
    fun candyDaoGetCandyById_noItemFound() = runBlocking {
        addDataToDb()
        val item = candyDao.getCandyById(987)
        assertNull(item)
    }
}
