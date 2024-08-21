package prodcontest.data.recommendations.datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import org.koin.java.KoinJavaComponent.inject
import prodcontest.data.recommendations.response.RecommendationDetailsResponseModel
import prodcontest.data.recommendations.response.RecommendationsResponseModel


internal class RecommendationsRemoteDataSource {

    private val httpClient : HttpClient by inject(HttpClient::class.java)

    suspend fun getRecommendations(lat: Double, lon : Double, page : Int, count : Int) = httpClient.get {
        url(GET_RECOMMENDATIONS_PATH)
        parameter(LOCATION_PARAM, "$lat,$lon")
        parameter(API_KEY_PARAM, API_KEY)
        parameter(VERSION_PARAM, VERSION)
        parameter(PAGE_PARAM, page)
        parameter(MAX_PARAM, count)
    }.body<RecommendationsResponseModel>()

    suspend fun getRecommendations(lat: Double, lon : Double) = httpClient.get {
        url(GET_RECOMMENDATIONS_PATH)
        parameter(LOCATION_PARAM, "$lat,$lon")
        parameter(API_KEY_PARAM, API_KEY)
        parameter(VERSION_PARAM, VERSION)
        parameter(RADIUS_PARAM, RADIUS)
    }.body<RecommendationsResponseModel>()

    suspend fun getRecommendationDetails(id : String) = httpClient.get {
        url("$GET_RECOMMENDATION_DETAILS$id/")
        parameter(VENUE_PARAM, id)
        parameter(API_KEY_PARAM, API_KEY)
        parameter(VERSION_PARAM, VERSION)
    }.body<RecommendationDetailsResponseModel>()

    companion object {
        private const val ROOT = "https://api.foursquare.com/v2"
        private const val GET_RECOMMENDATIONS_PATH = "$ROOT/search/recommendations"
        private const val GET_RECOMMENDATION_DETAILS = "$ROOT/venues/"

        private const val LOCATION_PARAM = "ll"
        private const val API_KEY_PARAM = "oauth_token"
        private const val API_KEY = "secret"
        private const val VERSION_PARAM = "v"
        private const val VERSION = "20240317"
        private const val PAGE_PARAM = "offset"
        private const val MAX_PARAM = "limit"
        private const val VENUE_PARAM = "venue_id"
        private const val RADIUS_PARAM = "radius"
        private const val RADIUS = 10000
    }
}