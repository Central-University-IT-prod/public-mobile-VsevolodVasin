package prodcontest.domain.leisure.repository

import kotlinx.coroutines.flow.Flow
import prodcontest.domain.leisure.model.LeisureModel

interface LeisureRepository {
    fun getLeisureList(): Flow<List<LeisureModel>>

    suspend fun getLeisure(id : Int): LeisureModel

    suspend fun addLeisure(leisureModel: LeisureModel)

    suspend fun removeLeisure(uid: Int)
}