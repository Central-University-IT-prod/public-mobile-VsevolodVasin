package prodcontest.data.leisure.datasource

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import prodcontest.data.leisure.dbmodel.LeisureDatabaseModel
import prodcontest.data.recommendations.response.RecommendationDetailsDatabaseModel

@Dao
interface LeisureDao {

    @Query("SELECT * FROM leisure")
    fun getLeisureList(): Flow<List<LeisureDatabaseModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeisure(leisure: LeisureDatabaseModel)

    @Query("SELECT * FROM leisure WHERE uid = :uid")
    suspend fun getLeisure(uid : Int) : LeisureDatabaseModel

    @Query("DELETE FROM leisure WHERE uid = :uid")
    suspend fun removeLeisure(uid : Int)
}