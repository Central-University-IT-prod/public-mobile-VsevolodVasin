package prodcontest.lifestylehub.presentation.home.map

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent
import prodcontest.domain.location.model.LocationModel
import prodcontest.domain.location.repository.LocationRepository
import prodcontest.domain.recommendation.model.RecommendationModel
import prodcontest.domain.recommendation.repository.RecommendationsRepository
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

sealed interface LocationUiState {
    data class Success(val location: LocationModel) : LocationUiState
    data object Error : LocationUiState
    data object Loading : LocationUiState
}

sealed interface RecommendationsUiState {
    data class Success(val recommendations: List<RecommendationModel>) : RecommendationsUiState
    data object NothingToLoad : RecommendationsUiState
    data object Loading : RecommendationsUiState
}

class MapViewModel : ViewModel()  {

    private val recommendationsRepository : RecommendationsRepository by KoinJavaComponent.inject(
        RecommendationsRepository::class.java
    )

    private val locationRepository : LocationRepository by KoinJavaComponent.inject(
        LocationRepository::class.java
    )

    private var lastZoomPosition = Point(0.0, 0.0)

    var locationUiState : LocationUiState by mutableStateOf(LocationUiState.Loading)
        private set

    var recommendationsUiState : RecommendationsUiState by mutableStateOf(RecommendationsUiState.NothingToLoad)
        private set


    init {
        getLocation()
    }

    fun getRecommendations(lat : Double, long: Double) {
        viewModelScope.launch {
            recommendationsUiState = RecommendationsUiState.Loading
            recommendationsUiState = try {
                RecommendationsUiState.Success(recommendationsRepository.getRecommendations(lat, long))
            } catch (e : Exception) {
                RecommendationsUiState.NothingToLoad
            }
        }
    }

    fun getLocation() {
        viewModelScope.launch {
            locationUiState = LocationUiState.Loading
            locationUiState = try {
                LocationUiState.Success(locationRepository.getLocation())
            } catch (e : Exception) {
                LocationUiState.Error
            }
        }
    }

    private fun distanceInMeters(point1: Point, point2: Point): Double {
        val earthRadius = 6371000 // Радиус Земли в метрах
        val dLat = Math.toRadians(point2.latitude - point1.latitude)
        val dLon = Math.toRadians(point2.longitude - point1.longitude)

        val a = sin(dLat/2) * sin(dLat/2) +
                cos(Math.toRadians(point1.latitude)) * cos(Math.toRadians(point2.latitude)) *
                sin(dLon/2) * sin(dLon/2)
        val c = 2 * atan2(sqrt(a), sqrt(1-a))

        return earthRadius * c
    }

    private fun isInRadius(point1: Point, point2: Point, radiusInMeters: Double): Boolean {
        val distance = distanceInMeters(point1, point2)
        return distance <= radiusInMeters
    }


    fun checkNeedLoad(newPosition : Point): Boolean {
        val need = !isInRadius(lastZoomPosition, newPosition, 10000.0)
        if (need) {
            lastZoomPosition = newPosition
        }
        return need
    }
}