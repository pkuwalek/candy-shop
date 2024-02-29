package com.example.candyshop.data

import com.example.candyshop.network.CandyApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val candyItemsRepository: CandyItemsRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://www.themealdb.com/api/json/v1/1/"

    // Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService : CandyApiService by lazy {
        retrofit.create(CandyApiService::class.java)
    }

    override val candyItemsRepository: CandyItemsRepository by lazy {
        NetworkCandyItemsRepository(retrofitService)
    }
}