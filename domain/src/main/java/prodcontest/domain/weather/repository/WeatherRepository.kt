package prodcontest.domain.weather.repository

import prodcontest.domain.weather.model.WeatherModel

interface WeatherRepository {
    suspend fun getWeather(): WeatherModel
}