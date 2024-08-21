package prodcontest.lifestylehub.presentation.home.recommendation.universalRecommendations

import androidx.compose.runtime.Composable


enum class RecommendationType { Place }
data class RecommendationUi(val id : String, val type : RecommendationType, val content : @Composable () -> Unit)
