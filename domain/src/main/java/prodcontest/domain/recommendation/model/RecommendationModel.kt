package prodcontest.domain.recommendation.model

data class RecommendationModel (
    val id : String,
    val name: String,
    val imageUrl: String,
    val address: String,
    val category : List<Category>,
    val coordinates : Coordinates
) {
    data class Category(
        val iconUrl : String,
        val name : String
    )

    data class Coordinates(
        val lat : Double,
        val long : Double
    )
}