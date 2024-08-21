package prodcontest.data.leisure


import prodcontest.data.leisure.dbmodel.LeisureDatabaseModel
import prodcontest.data.recommendations.response.RecommendationDetailsResponseModel
import prodcontest.data.recommendations.response.RecommendationsResponseModel
import prodcontest.domain.leisure.model.LeisureModel
import prodcontest.domain.recommendation.model.RecommendationDetailsModel
import prodcontest.domain.recommendation.model.RecommendationModel


internal fun List<LeisureDatabaseModel>.mapToDomainModels() : List<LeisureModel> {
    return this.map {
        LeisureModel(
            name = it.name,
            description = it.description,
            date = it.date,
            recommendation = it.recommendation,
            id = it.uid!!
        )
    }
}

internal fun LeisureDatabaseModel.mapToDomainModel() : LeisureModel {
    return LeisureModel(
            name = this.name,
            description = this.description,
            date = this.date,
            recommendation = this.recommendation,
            id = this.uid!!
        )
}