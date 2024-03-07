package com.example.candyshop.api

import com.example.candyshop.data.db.CandyDatabase
import com.example.candyshop.data.model.Candy
import com.example.candyshop.data.Resource
import com.example.candyshop.data.toCandy
import com.example.candyshop.data.toCandyEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface CandyItemsRepository {
    suspend fun getCandyItems(
        forceFetchFromRemote: Boolean
    ): Flow<Resource<List<Candy>>>

    suspend fun getCandy(id: Int): Flow<Resource<Candy>>
}

class CandyItemsRepositoryImplementation @Inject constructor(
    private val candyApiService: CandyApiService,
    private val candyDatabase: CandyDatabase
) : CandyItemsRepository {
    override suspend fun getCandyItems(forceFetchFromRemote: Boolean): Flow<Resource<List<Candy>>> {
        return flow {
            val localCandyList = candyDatabase.candyDao.getAllCandy()
            val shouldLoadLocalCandy = localCandyList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalCandy) {
                emit(Resource.Success(data = localCandyList.map { candyEntity ->
                    candyEntity.toCandy()
                }))
                return@flow
            }

            val candyListFromApi = try {
                candyApiService.getDesserts()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading desserts. IOException"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading desserts. HTTPException"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading desserts. GeneralException"))
                return@flow
            }

            val candyEntities = candyListFromApi.meals.let {
                it.map { candyDto ->
                    candyDto.toCandyEntity()
                }
            }

            candyDatabase.candyDao.upsertCandyList(candyEntities)
            emit(Resource.Success(
                candyEntities.map { it.toCandy() }
            ))
        }
    }

    override suspend fun getCandy(id: Int): Flow<Resource<Candy>> {
        return flow {
            val candyEntity = candyDatabase.candyDao.getCandyById(id)
            if (candyEntity != null) {
                emit(Resource.Success(candyEntity.toCandy()))
                return@flow
            }
            emit(Resource.Error("Error no dessert found."))
        }
    }
}