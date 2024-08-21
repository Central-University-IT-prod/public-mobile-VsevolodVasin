package prodcontest.lifestylehub.presentation.home.recommendation.universalRecommendations

import androidx.compose.runtime.Composable


enum class RecommendationType { Place, Activity }
data class RecommendationUi(val id : String, val navPath : String? = null, val type : RecommendationType, val content : @Composable () -> Unit)
