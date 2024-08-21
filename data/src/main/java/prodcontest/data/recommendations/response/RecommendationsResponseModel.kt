package prodcontest.data.recommendations.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RecommendationsResponseModel(
    @SerialName("response") val response: Response
) {
    @Serializable
    internal data class Response(
        @SerialName("group") val group: Group,
    )

    @Serializable
    internal data class Group(
        @SerialName("results") val results: List<Recommendation>,
        @SerialName("totalResults") val totalResults : Int
    )

    @Serializable
    internal data class Recommendation(
        @SerialName("venue") val venue: Venue,
        @SerialName("photo") val photo : Image? = null,
    )

    @Serializable
    internal data class Venue(
        @SerialName("id") val id: String,
        @SerialName("name") val name: String,
        @SerialName("location") val location : Location,
        @SerialName("categories") val categories : List<Category>,
    )

    @Serializable
    internal data class Location(
        @SerialName("address") val address: String = "",
        @SerialName("lat") val lat: Double,
        @SerialName("lng") val lng: Double,
    )

    @Serializable
    internal data class Category(
        @SerialName("name") val name: String,
        @SerialName("icon") val icon: Image,
    )

    @Serializable
    internal data class Image(
        @SerialName("prefix") val prefix: String,
        @SerialName("suffix") val suffix: String,
    )
}