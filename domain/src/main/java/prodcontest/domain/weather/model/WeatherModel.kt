package prodcontest.domain.weather.model

data class WeatherModel (
    val temperature: Temperature,
    val weather: Weather,
    val city : String
) {
    data class Weather(
        val icon : String,
        val name : String
    )
    data class Temperature(
        val real : Double,
        val weatherMin: Double,
        val weatherMax: Double,
        val feel: Double,
    )
}