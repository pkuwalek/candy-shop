package com.example.candyshop.di

import android.app.Application
import androidx.room.Room
import com.example.candyshop.api.CandyApiService
import com.example.candyshop.data.db.CandyDatabase
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun providesCandyApi() : CandyApiService {
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .client(client)
            .build()
            .create(CandyApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesCandyDatabase(app: Application): CandyDatabase {
        return Room.databaseBuilder(
            app,
            CandyDatabase::class.java,
            "dessert.db"
        ).build()
    }
}