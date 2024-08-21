package prodcontest.data.weather


import prodcontest.data.weather.response.WeatherResponseModel
import prodcontest.domain.weather.model.WeatherModel



internal fun WeatherResponseModel.mapToDomainModel() = WeatherModel(
    temperature = WeatherModel.Temperature(
        real = main.temp,
        weatherMin = main.tempMin,
        weatherMax =  main.tempMax,
        feel = main.feelsLike
    ),
    weather = WeatherModel.Weather(
        icon = "weather_${weather.first().icon}",
        name = weather.first().description
    ),
    city = name
)