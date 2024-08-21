package prodcontest.data.recommendations.datasource.local

import kotlinx.serialization.json.Json
import org.koin.java.KoinJavaComponent.inject
import prodcontest.data.recommendations.response.RecommendationDetailsDatabaseModel
import prodcontest.data.recommendations.response.RecommendationDetailsResponseModel


internal class RecommendationsLocalDataSource {

    private val dao : RecommendationsDao by inject(RecommendationsDao::class.java)

    suspend fun getRecommendationDetails(id : String) : RecommendationDetailsResponseModel? {
        return dao.getRecommendationById(id)?.json?.let { Json.decodeFromString(it) }
    }

    suspend fun saveRecommendationDetails(id : String, json : String) {
        return dao.insertRecommendation(RecommendationDetailsDatabaseModel(id=id, json = json, timeStamp = System.currentTimeMillis()))
    }

    suspend fun cleanOutdated() = dao.cleanOutdated(System.currentTimeMillis()-6*60*60*1000)
}