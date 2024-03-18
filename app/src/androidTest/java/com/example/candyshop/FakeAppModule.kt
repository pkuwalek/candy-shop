package com.example.candyshop

import com.example.candyshop.api.CandyItemsRepository
import com.example.candyshop.api.CandyItemsRepositoryImplementation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//abstract class FakeAppModule {
//
//    @Binds
//    @Singleton
//    abstract fun bindCandyItemsRepository(
//        fakeCandyItemsRepositoryImplementation: CandyItemsRepositoryImplementation
//    ): CandyItemsRepository
//
//}