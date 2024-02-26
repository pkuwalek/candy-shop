package com.example.candyshop.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

// define how retrofit talks to the web server using HTTP requests
interface CandyApiService {
    @GET("filter.php?c=Dessert")
    suspend fun getDesserts(): String
}

object CandyApi {
    val retrofitService : CandyApiService by lazy {
        retrofit.create(CandyApiService::class.java)
    }
}