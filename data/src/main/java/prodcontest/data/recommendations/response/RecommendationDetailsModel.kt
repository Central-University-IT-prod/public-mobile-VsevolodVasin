package prodcontest.data.recommendations.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RecommendationDetailsResponseModel(
    @SerialName("response") val response: Response
) {
    @Serializable
    internal data class Response(
        @SerialName("venue") val venue: Venue,
    )


    @Serializable
    internal data class Venue(
        @SerialName("id") val id: String,
        @SerialName("name") val name: String,
        @SerialName("description") val description : String?= null,
        @SerialName("bestPhoto") val bestPhoto: Image? = null,
        @SerialName("photos") val photos: Photos? = null,
        @SerialName("location") val location : Location,
        @SerialName("categories") val categories : List<Category>,
        @SerialName("contact") val contact: Contact? = null,
        @SerialName("header") val header : String? = null,
        @SerialName("popular") val popular : Popular? = null,
    )
    @Serializable
    internal data class Popular(
        @SerialName("timeframes")  val timeframes : List<Timeframe>
    )

    @Serializable
    internal data class Timeframe(
        @SerialName("days")  val name: String,
        @SerialName("open") val open : List<Open>
    ) {
        @Serializable
        internal data class Open(
            @SerialName("renderedTime")  val renderedTime : String
        )
    }



    @Serializable
    internal data class Photos(
        @SerialName("groups") val groups: List<PhotosGroups>,
    )

    @Serializable
    internal data class Contact(
        @SerialName("phone") val phone: String? = null,
        @SerialName("formattedPhone") val formattedPhone: String? = null,
    )

    @Serializable
    internal data class PhotosGroups(
        @SerialName("name") val name: String,
        @SerialName("items") val items: List<Image>,
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
