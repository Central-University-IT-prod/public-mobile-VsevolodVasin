package prodcontest.domain.recommendation.model


data class RecommendationDetailsModel(
    val id: String,
    val name: String,
    val imageUrl: String,
    val images: List<String>,
    val contactPhone: String? = null,
    val contactFormattedPhone: String ?= null,
    val description: String,
    val address: String,
    val category: List<Category>,
    val workingSegments : List<WorkingSegment>?
) {

    data class WorkingSegment(
        val name : String,
        val hours : String
    )
    data class Category(
        val iconUrl : String,
        val name : String
    )
}