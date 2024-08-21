package prodcontest.data

import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.dsl.module
import prodcontest.data.location.datasource.LocationDataSource
import prodcontest.data.location.datasource.LocationLocalDataSourceImpl
import prodcontest.data.location.repository.LocationRepositoryImpl
import prodcontest.data.weather.datasource.WeatherRemoteDataSource
import prodcontest.data.weather.repository.WeatherRepositoryImpl
import prodcontest.data.weather.response.WeatherResponseModel
import prodcontest.domain.location.model.LocationModel
import prodcontest.domain.location.repository.LocationRepository
import prodcontest.domain.weather.model.WeatherModel

class WeatherDataLayerTest {

    val lat = 48.0
    val lon = 47.0

    var weatherDataSourceLocation : Pair<Double, Double>? = null

    private val locationDataSource = object : LocationDataSource {
        override suspend fun getLocation(): LocationModel {
            return LocationModel(lat, lon)
        }
    }

    private val weatherDataSource = object : WeatherRemoteDataSource {
        override suspend fun getCurrentWeather(lat: Double, lon: Double): WeatherResponseModel {
            weatherDataSourceLocation = Pair(lat, lon)
            return WeatherResponseModel(
                name="Москва",
                main=WeatherResponseModel.Main(
                    temp = 15.0,
                    feelsLike = 13.0,
                    tempMin = 10.0,
                    tempMax = 20.0
                ),
                weather = listOf(
                    WeatherResponseModel.Weather(
                        main="Cloudy",
                        description = "облачно",
                        icon = "01d"
                    )
                )
            )
        }
    }

    private val weatherModel = WeatherModel(
        city = "Москва",
        temperature = WeatherModel.Temperature(
            real=15.0,
            feel = 13.0,
            weatherMin = 10.0,
            weatherMax = 20.0
        ),
        weather = WeatherModel.Weather(
            icon = "weather_01d",
            name = "облачно"
        )
    )

    init {
        GlobalContext.startKoin {
            modules(
                module {
                    single<WeatherRemoteDataSource> { weatherDataSource  }
                    single <LocationRepository> { LocationRepositoryImpl() }
                    single { LocationLocalDataSourceImpl() }
                    single<LocationDataSource> { locationDataSource }
                })
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun checkLocationDeliveryToWeather() {
       runBlocking {
           val weatherRepository = WeatherRepositoryImpl()
           weatherRepository.getWeather()
           assert(weatherDataSourceLocation?.first == lat && weatherDataSourceLocation?.second == lon)
       }
    }

    @Test
    fun checkWeatherParsing() {
        runBlocking {
            val weatherRepository = WeatherRepositoryImpl()
            val weather = weatherRepository.getWeather()
            assert(weather==weatherModel)
        }
    }

}