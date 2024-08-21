package prodcontest.data.weather.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponseModel(
    @SerialName("name") val name: String,
    @SerialName("main") val main: Main,
    @SerialName("weather") val weather: List<Weather>,

    ) {
    @Serializable
    data class Main(
        @SerialName("temp") val temp: Double,
        @SerialName("feels_like") val feelsLike: Double,
        @SerialName("temp_min") val tempMin: Double,
        @SerialName("temp_max") val tempMax: Double,
    )

    @Serializable
    data class Weather(
        @SerialName("main") val main: String,
        @SerialName("description") val description: String,
        @SerialName("icon") val icon: String,
    )
}