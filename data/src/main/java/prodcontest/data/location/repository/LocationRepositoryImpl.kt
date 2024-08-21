package prodcontest.data.location.repository

import org.koin.java.KoinJavaComponent.inject
import prodcontest.data.location.datasource.LocationDataSource
import prodcontest.data.location.datasource.LocationLocalDataSource
import prodcontest.domain.location.model.LocationModel
import prodcontest.domain.location.repository.LocationRepository

class LocationRepositoryImpl : LocationRepository {

    private val dataSource : LocationDataSource by inject(
        LocationDataSource::class.java
    )
    private val localDataSource : LocationLocalDataSource by inject(
        LocationLocalDataSource::class.java
    )

    override suspend fun getLocation() : LocationModel {
        val localLocation = localDataSource.getLocation()
        return if (localLocation!=null) {
            localLocation
        } else {
            val newLocation = dataSource.getLocation()
            localDataSource.setLocation(newLocation)
            newLocation
        }
    }
}