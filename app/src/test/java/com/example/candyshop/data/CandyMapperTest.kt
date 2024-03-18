package com.example.candyshop.data

import com.example.candyshop.api.CandyDto
import com.example.candyshop.data.db.CandyEntity
import com.example.candyshop.data.model.Candy
import com.example.candyshop.data.toCandy
import com.example.candyshop.data.toCandyEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class CandyMapperTest {
    private val candyDto = CandyDto(
        name = "CandyName",
        imageUrl = "CandyImage",
        id = "12345",
        price = 10
    )
    private val candyEntity = CandyEntity(
        name = "CandyName",
        imageUrl = "CandyImage",
        id = "12345",
        price = 10
    )
    private val candy = Candy(
        name = "CandyName",
        imageUrl = "CandyImage",
        id = "12345",
        price = 10
    )

    @Test
    fun candyMapper_candyDtoToEntity_verifyCandyEntity() {
        assertEquals(candyEntity, candyDto.toCandyEntity())
    }

    @Test
    fun candyMapper_candyEntityToCandy_verifyCandy() {
        assertEquals(candy, candyEntity.toCandy())
    }
}