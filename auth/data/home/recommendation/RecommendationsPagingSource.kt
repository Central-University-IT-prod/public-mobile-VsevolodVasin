package prodcontest.lifestylehub.presentation.home.recommendation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.paging.PagingSource
import androidx.paging.PagingState
import prodcontest.domain.recommendation.model.RecommendationModel
import prodcontest.domain.recommendation.repository.RecommendationsRepository
import prodcontest.lifestylehub.presentation.home.recommendation.universalRecommendations.RecommendationType
import prodcontest.lifestylehub.presentation.home.recommendation.universalRecommendations.RecommendationUi
import timber.log.Timber

class RecommendationsPagingSource(private val recommendationsRepository: RecommendationsRepository, private val navController: NavController) : PagingSource<Int, RecommendationUi>() {

    override fun getRefreshKey(state: PagingState<Int, RecommendationUi>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecommendationUi> {
        return try {
            val nextPageNumber = params.key ?: 0

            val response = recommendationsRepository.getRecommendations(nextPageNumber)

            val ui = response.second.map {
                RecommendationUi(
                    it.id,
                    type = RecommendationType.Place
                ) {
                    RecommendationCard(recommendationModel = it, navController=navController)
                }
            }

            LoadResult.Page(
                data = ui,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber-1,
                nextKey = if ((response.first/10)+1 == nextPageNumber) null else nextPageNumber+1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}