package prodcontest.data.activity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ActivityResponseModel(
    @SerialName("activity") val activity: String,
    @SerialName("type") val type: String,
)