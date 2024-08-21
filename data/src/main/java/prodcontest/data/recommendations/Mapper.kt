package prodcontest.data.recommendations


import prodcontest.data.recommendations.response.RecommendationDetailsResponseModel
import prodcontest.data.recommendations.response.RecommendationsResponseModel
import prodcontest.domain.recommendation.model.RecommendationDetailsModel
import prodcontest.domain.recommendation.model.RecommendationModel


internal fun RecommendationsResponseModel.mapToDomainModels() : List<RecommendationModel> {
    return this.response.group.results.map { it ->
        RecommendationModel(
            id = it.venue.id,
            name = it.venue.name,
            imageUrl = if(it.photo!=null) { it.photo.prefix+"1920x1080"+it.photo.suffix } else { "" },
            address = it.venue.location.address,
            category = it.venue.categories.map { category ->  RecommendationModel.Category(name=category.name, iconUrl = category.icon.prefix+category.icon.suffix) },
            coordinates = RecommendationModel.Coordinates(it.venue.location.lat, it.venue.location.lng)

        )
    }
}

internal fun RecommendationDetailsResponseModel.mapToDomainModel() : RecommendationDetailsModel {
    return RecommendationDetailsModel(
            id =  this.response.venue.id,
            name = this.response.venue.name,
            imageUrl= if(this.response.venue.bestPhoto!=null) { this.response.venue.bestPhoto.prefix+"1920x1080"+this.response.venue.bestPhoto.suffix } else { "" } ,
            address =  this.response.venue.location.address,
            category = this.response.venue.categories.map { category ->  RecommendationDetailsModel.Category(name=category.name, iconUrl = category.icon.prefix+category.icon.suffix) },
            contactPhone = this.response.venue.contact?.phone,
            contactFormattedPhone = this.response.venue.contact?.formattedPhone,
            description = this.response.venue.description ?: "",
            workingSegments = this.response.venue.popular?.timeframes?.map {
                 RecommendationDetailsModel.WorkingSegment(
                     name = it.name,
                     hours = it.open.joinToString(postfix = "\n") { it.renderedTime }
                 )

            },
            images = this.response.venue.photos?.groups?.map {
                    it.items.map {
                        it.prefix+"1920x1080"+it.suffix
                    }
                }?.flatten() ?: emptyList()

        )

}