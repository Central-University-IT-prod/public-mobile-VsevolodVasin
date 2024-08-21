package prodcontest.data.weather.datasource

import prodcontest.data.weather.response.WeatherResponseModel

interface WeatherRemoteDataSource {
    suspend fun getCurrentWeather(lat: Double, lon : Double) : WeatherResponseModel
}