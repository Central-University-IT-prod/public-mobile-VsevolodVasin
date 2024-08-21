package prodcontest.data.weather

import prodcontest.data.weather.response.WeatherResponseModel
import prodcontest.domain.weather.model.WeatherModel

internal const val iconBasePath = "http://openweathermap.org/img/w/"

internal fun WeatherResponseModel.mapToDomainModel() = WeatherModel(
    temperature = WeatherModel.Temperature(
        real = main.temp,
        weatherMin = main.tempMin,
        weatherMax =  main.tempMax,
        feel = main.feelsLike
    ),
    weather = WeatherModel.Weather(
        iconUrl = "$iconBasePath${weather.first().icon}.png",
        name = weather.first().description
    ),
    city = name
)