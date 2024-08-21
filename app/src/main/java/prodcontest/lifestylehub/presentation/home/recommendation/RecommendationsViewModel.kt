package prodcontest.lifestylehub.presentation.home.recommendation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.filter
import kotlinx.coroutines.flow.map
import org.koin.java.KoinJavaComponent.inject
import prodcontest.domain.activity.repository.ActivityRepository
import prodcontest.domain.recommendation.repository.RecommendationsRepository

class RecommendationsViewModel : ViewModel()  {

    private val recommendationsRepository : RecommendationsRepository by inject(RecommendationsRepository::class.java)
    private val activityRepository : ActivityRepository by inject(ActivityRepository::class.java)

    var flow = Pager(
        PagingConfig(pageSize = 10)
    ) {
        RecommendationsPagingSource(recommendationsRepository, activityRepository)
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