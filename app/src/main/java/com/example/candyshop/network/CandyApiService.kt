package com.example.candyshop.network

import retrofit2.http.GET

// define how retrofit talks to the web server using HTTP requests
interface CandyApiService {
    @GET("filter.php?c=Dessert")
    suspend fun getDesserts(): Meals
}
