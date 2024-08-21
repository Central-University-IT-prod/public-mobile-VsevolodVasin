package prodcontest.lifestylehub.presentation.home.recommendation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import prodcontest.domain.activity.repository.ActivityRepository
import prodcontest.domain.recommendation.repository.RecommendationsRepository
import prodcontest.lifestylehub.presentation.home.activity.ActivitySuccess
import prodcontest.lifestylehub.presentation.home.recommendation.universalRecommendations.RecommendationType
import prodcontest.lifestylehub.presentation.home.recommendation.universalRecommendations.RecommendationUi
import prodcontest.lifestylehub.presentation.navigation.Destinations

class RecommendationsPagingSource(
    private val recommendationsRepository: RecommendationsRepository,
    private val activityRepository: ActivityRepository,
) : PagingSource<Int, RecommendationUi>() {

    companion object Settings {
        const val itemsPerPage = 10
    }

    override fun getRefreshKey(state: PagingState<Int, RecommendationUi>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecommendationUi> {
        return try {
            val nextPageNumber = params.key ?: 0

            val response = recommendationsRepository.getRecommendations(nextPageNumber, itemsPerPage-1)

            var recommendationsCount = 0

            val ui = response.second.map {
                recommendationsCount+=1
                RecommendationUi(
                    it.id,
                    navPath = "${Destinations.PlaceDetails.route}?recommendationId=${it.id}",
                    type = RecommendationType.Place
                ) {
                    RecommendationCard(recommendationModel = it)
                }
            }.toMutableList()

            val activity = activityRepository.getActivity(easy = false)
            ui.add(
                RecommendationUi(
                    activity.name,
                    type=RecommendationType.Activity
                ) {
                    ActivitySuccess(activity)
                }
            )

            ui.shuffle()

            val itemsCount = (response.first/itemsPerPage)+response.first
            val pagesCount = itemsCount / itemsPerPage
            LoadResult.Page(
                data = ui,
                prevKey = null,
                nextKey = if (recommendationsCount==0 || (pagesCount+1 == nextPageNumber)) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}