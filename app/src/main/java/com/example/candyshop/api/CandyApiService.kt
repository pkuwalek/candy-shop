package com.example.candyshop.api

import retrofit2.http.GET

// defines how retrofit talks to the web server using HTTP requests
interface CandyApiService {
    @GET("filter.php?c=Dessert")
    suspend fun getDesserts(): CandyListDto
}
