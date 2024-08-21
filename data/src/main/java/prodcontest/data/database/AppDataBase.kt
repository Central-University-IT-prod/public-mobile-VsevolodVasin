package prodcontest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import prodcontest.data.leisure.datasource.LeisureDao
import prodcontest.data.leisure.dbmodel.LeisureDatabaseModel
import prodcontest.data.recommendations.datasource.local.RecommendationsDao
import prodcontest.data.recommendations.response.RecommendationDetailsDatabaseModel

@Database(
    entities = [RecommendationDetailsDatabaseModel::class, LeisureDatabaseModel::class],
    version = 1
)
abstract class AppDataBase: RoomDatabase() {

    abstract val recommendationsDao: RecommendationsDao

    abstract val leisureDao: LeisureDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}