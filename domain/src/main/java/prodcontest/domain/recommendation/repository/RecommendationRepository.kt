package prodcontest.domain.recommendation.repository

import prodcontest.domain.recommendation.model.RecommendationDetailsModel
import prodcontest.domain.recommendation.model.RecommendationModel

interface RecommendationsRepository {
    suspend fun getRecommendations(page : Int, count : Int): Pair<Int, List<RecommendationModel>>

    suspend fun getRecommendations(lat : Double, long : Double): List<RecommendationModel>

    suspend fun getRecommendationDetails(id : String) : RecommendationDetailsModel

}