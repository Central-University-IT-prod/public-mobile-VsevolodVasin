package prodcontest.auth.data.users.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponseModel(
    @SerialName("results") val results : List<User>
) {

    @Serializable
    data class User (
        @SerialName("gender") var gender     : String,
        @SerialName("name") var name       : Name,
        @SerialName("email") var email      : String,
        @SerialName("login") var login : Login,
        @SerialName("phone") var phone      : String,
        @SerialName("picture") var picture    : Picture?    = null,
    )

    @Serializable
    data class Picture(
        @SerialName("large") var large     : String,
        @SerialName("medium") var medium    : String,
        @SerialName("thumbnail") var thumbnail : String
    )

    @Serializable
    data class Name(
        @SerialName("title") var title : String,
        @SerialName("first") var first : String,
        @SerialName("last") var last  : String
    )

    @Serializable
    data class Login (
        @SerialName("uuid") var uuid     : String,
        @SerialName("username") var username : String,
    )
}