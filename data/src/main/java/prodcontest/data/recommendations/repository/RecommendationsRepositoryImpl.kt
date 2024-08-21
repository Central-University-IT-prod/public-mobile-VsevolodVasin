package prodcontest.data.recommendations.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.java.KoinJavaComponent.inject
import prodcontest.data.recommendations.datasource.RecommendationsRemoteDataSource
import prodcontest.data.recommendations.datasource.local.RecommendationsLocalDataSource
import prodcontest.data.recommendations.mapToDomainModel
import prodcontest.data.recommendations.mapToDomainModels
import prodcontest.domain.location.repository.LocationRepository
import prodcontest.domain.recommendation.model.RecommendationDetailsModel
import prodcontest.domain.recommendation.model.RecommendationModel
import prodcontest.domain.recommendation.repository.RecommendationsRepository

class RecommendationsRepositoryImpl : RecommendationsRepository {

    private val remoteDataSource : RecommendationsRemoteDataSource by inject(RecommendationsRemoteDataSource::class.java)
    private val localDataSource : RecommendationsLocalDataSource by inject(RecommendationsLocalDataSource::class.java)
    private val locationRepository : LocationRepository by inject(LocationRepository::class.java)

    init {
        CoroutineScope(Dispatchers.IO).launch { cleanOutdated() }
    }

    override suspend fun getRecommendations(page: Int, count : Int): Pair<Int, List<RecommendationModel>> {
        val location = locationRepository.getLocation()
        return try {
            val response = remoteDataSource.getRecommendations(lat=location.latitude, lon=location.longitude, page=page, count=count)
            Pair(response.response.group.totalResults, response.mapToDomainModels())
        } catch (_ : Exception) {
            Pair(0, emptyList())
        }
    }

    override suspend fun getRecommendations(lat : Double, long : Double): List<RecommendationModel> {
        return try {
            val response = remoteDataSource.getRecommendations(lat=lat, lon=long)
            response.mapToDomainModels()
        } catch (_ : Exception) {
            emptyList()
        }
    }

    override suspend fun getRecommendationDetails(id: String): RecommendationDetailsModel {
        val localDetails = localDataSource.getRecommendationDetails(id)
        return if (localDetails!=null) {
            localDetails.mapToDomainModel()
        } else {
            val detailsRemote = remoteDataSource.getRecommendationDetails(id)
            val json = Json.encodeToString(detailsRemote)
            localDataSource.saveRecommendationDetails(id, json)
            detailsRemote.mapToDomainModel()
        }
    }

    suspend fun cleanOutdated() {
        localDataSource.cleanOutdated()
    }
}