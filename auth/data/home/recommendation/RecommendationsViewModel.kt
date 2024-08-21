package prodcontest.lifestylehub.presentation.home.recommendation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.java.KoinJavaComponent.inject
import prodcontest.domain.recommendation.repository.RecommendationsRepository
import prodcontest.lifestylehub.presentation.home.recommendation.universalRecommendations.RecommendationUi

class RecommendationsViewModel : ViewModel()  {

    private val recommendationsRepository : RecommendationsRepository by inject(RecommendationsRepository::class.java)

    var flow : Flow<PagingData<RecommendationUi>>? = null

    fun setupPager(navController: NavController) {
        flow = Pager(
            PagingConfig(pageSize = 10)
        ) {
            RecommendationsPagingSource(recommendationsRepository, navController)
        }.flow.map {
            val recommendationMap = mutableSetOf<String>()
            it.filter { recommendation ->
                if (recommendationMap.contains(recommendation.id)) {
                    false
                } else {
                    recommendationMap.add(recommendation.id)
                }
            }
        }
        .cachedIn(viewModelScope)
    }

}