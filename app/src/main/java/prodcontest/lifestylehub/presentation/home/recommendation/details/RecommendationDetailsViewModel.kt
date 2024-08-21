package prodcontest.lifestylehub.presentation.home.recommendation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent
import prodcontest.domain.leisure.model.LeisureModel
import prodcontest.domain.leisure.repository.LeisureRepository
import prodcontest.domain.recommendation.model.RecommendationDetailsModel
import prodcontest.domain.recommendation.repository.RecommendationsRepository

sealed interface RecommendationDetailsUiState {
    data class Success(val recommendation: RecommendationDetailsModel) : RecommendationDetailsUiState
    data object Error : RecommendationDetailsUiState
    data object Loading : RecommendationDetailsUiState
}


class RecommendationDetailsViewModel : ViewModel() {
    private val recommendationsRepository : RecommendationsRepository by KoinJavaComponent.inject(
        RecommendationsRepository::class.java
    )

    private val leisureRepository : LeisureRepository by KoinJavaComponent.inject(
        LeisureRepository::class.java
    )

    var recommendationUiState: RecommendationDetailsUiState by mutableStateOf(RecommendationDetailsUiState.Loading)
        private set


    fun getDetails(id : String) {
        viewModelScope.launch {
            viewModelScope.launch {
                recommendationUiState = RecommendationDetailsUiState.Loading
                recommendationUiState = try {
                    RecommendationDetailsUiState.Success(recommendationsRepository.getRecommendationDetails(id))
                } catch (_ : Exception) {
                    RecommendationDetailsUiState.Error
                }
            }
        }
    }

    fun saveLeisure(model : LeisureModel) {
        viewModelScope.launch {
            leisureRepository.addLeisure(
               model
            )
        }
    }

}