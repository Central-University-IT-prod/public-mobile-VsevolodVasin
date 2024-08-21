package prodcontest.data.weather.repository

import kotlinx.coroutines.delay
import prodcontest.data.weather.mapToDomainModel
import org.koin.java.KoinJavaComponent.inject
import prodcontest.data.weather.datasource.WeatherRemoteDataSource
import prodcontest.domain.location.repository.LocationRepository
import prodcontest.domain.weather.model.WeatherModel
import prodcontest.domain.weather.repository.WeatherRepository

class WeatherRepositoryImpl : WeatherRepository {

    private val remoteDataSource : WeatherRemoteDataSource by inject(WeatherRemoteDataSource::class.java)
    private val locationRepository : LocationRepository by inject(LocationRepository::class.java)

    override suspend fun getWeather(): WeatherModel {
        val location = locationRepository.getLocation()
        return remoteDataSource.getCurrentWeather(lat=location.latitude, lon=location.longitude)
            .mapToDomainModel()
    }
}