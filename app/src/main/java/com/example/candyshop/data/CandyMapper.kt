package com.example.candyshop.data

import com.example.candyshop.data.db.CandyEntity
import com.example.candyshop.data.model.Candy
import com.example.candyshop.api.CandyDto

fun CandyDto.toCandyEntity(): CandyEntity {
    return CandyEntity(
        name = name ?: "",
        imageUrl = imageUrl ?: "",
        id = id ?: "",
        price = price
    )
}

fun CandyEntity.toCandy() : Candy {
    return Candy(
        name = name,
        imageUrl = imageUrl,
        id = id,
        price = price
    )
}