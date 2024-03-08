package com.example.candyshop.di

import com.example.candyshop.api.CandyItemsRepository
import com.example.candyshop.api.CandyItemsRepositoryImplementation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCandyItemsRepository(
        candyItemsRepositoryImplementation: CandyItemsRepositoryImplementation
    ): CandyItemsRepository
}