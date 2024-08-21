package prodcontest.data.weather.datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import org.koin.java.KoinJavaComponent.inject
import prodcontest.data.weather.response.WeatherResponseModel


internal class WeatherRemoteDataSourceImpl : WeatherRemoteDataSource {

    private val httpClient : HttpClient by inject(HttpClient::class.java)

    override suspend fun getCurrentWeather(lat: Double, lon : Double) = httpClient.get {
        url(GET_CURRENT_WEATHER_PATH)
        parameter(LAT_PARAM, lat)
        parameter(LON_PARAM, lon)
        parameter(API_KEY_PARAM, API_KEY)
        parameter(LANG, CURRENT_LANG)
        parameter(UNIT_PARAM, UNIT)
    }.body<WeatherResponseModel>()

    companion object {
        private const val ROOT = "https://api.openweathermap.org/data/2.5"
        private const val GET_CURRENT_WEATHER_PATH = "$ROOT/weather"

        private const val LAT_PARAM = "lat"
        private const val LON_PARAM = "lon"
        private const val API_KEY_PARAM = "appid"
        private const val API_KEY = "secret"
        private const val LANG = "lang"
        private const val CURRENT_LANG = "ru"
        private const val UNIT_PARAM = "units"
        private const val UNIT = "metric"
    }
}