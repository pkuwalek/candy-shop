package com.example.candyshop

import com.example.candyshop.database.TempDatabase
import com.example.candyshop.fake.FakeDataSource
import com.example.candyshop.ui.DetailsScreenViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class DetailsScreenViewModelTest {
    private val detailsScreenViewModel = DetailsScreenViewModel(
        temporaryDb = TempDatabase
    )

    @Before
    fun populateTempDatabase() {
        TempDatabase.allDesserts.addAll(FakeDataSource.dessertList.meals)
    }

    @Test
    fun detailsScreenViewModel_updateTextField_verifyTextFieldInput() {
        val sampleString = "Foo"
        detailsScreenViewModel.updateTextField(sampleString)
        assertEquals(sampleString, detailsScreenViewModel.textFieldInput)
    }

    @Test
    fun detailsScreenViewModel_getDessertById_verifyResultSuccess() {
        val sampleId = 12345
        val result = detailsScreenViewModel.getDessertById(sampleId)
        assertEquals(result, FakeDataSource.dessertList.meals[0])
    }

    @Test
    fun detailsScreenViewModel_getDessertById_verifyResultNotFound() {
        val sampleId = 14785
        assertNull(detailsScreenViewModel.getDessertById(sampleId))
    }

    @Test
    fun detailsScreenViewModel_getDessertById_verifyNullInput() {
        val sampleId = null
        assertNull(detailsScreenViewModel.getDessertById(sampleId))
    }
}