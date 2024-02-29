package com.example.candyshop.network

import retrofit2.http.GET
import retrofit2.http.Path

// define how retrofit talks to the web server using HTTP requests
interface CandyApiService {
    @GET("filter.php?c=Dessert")
    suspend fun getDesserts(): Meals

//    @GET("lookup.php?i={id}")
//    suspend fun getDessertById(@Path("id") id: Int): Meals
}
