package prodcontest.data.recommendations.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import prodcontest.data.recommendations.response.RecommendationDetailsDatabaseModel

@Dao
interface RecommendationsDao {

    @Query("SELECT * FROM recommendations WHERE id = :id")
    suspend fun getRecommendationById(id: String): RecommendationDetailsDatabaseModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecommendation(recommendation: RecommendationDetailsDatabaseModel)

    @Query("DELETE FROM recommendations WHERE timeStamp<=:deleteTimeStamp")
    suspend fun cleanOutdated(deleteTimeStamp : Long)

}