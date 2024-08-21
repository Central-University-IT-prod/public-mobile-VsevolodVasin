package prodcontest.data

import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.dsl.module
import prodcontest.data.location.datasource.LocationDataSource
import prodcontest.data.location.datasource.LocationLocalDataSource
import prodcontest.data.location.datasource.LocationLocalDataSourceImpl
import prodcontest.data.location.repository.LocationRepositoryImpl
import prodcontest.domain.location.model.LocationModel

class LocationDataLayerTest {

    val lat = 48.0
    val lon = 47.0

    val cachedLocationModel = LocationModel(32.0, 64.0)

    var hasCachedLocation = false

    private val locationDataSource = object : LocationDataSource {
        override suspend fun getLocation(): LocationModel {
            return LocationModel(lat, lon)
        }
    }


    var setLocationCalled = false

    private val locationLocalDataSource = object : LocationLocalDataSource {
        override fun getLocation(): LocationModel? {
            return if (hasCachedLocation) {
                cachedLocationModel
            } else {
                null
            }
        }

        override fun setLocation(location: LocationModel) {
            setLocationCalled = true
        }
    }


    init {
        GlobalContext.startKoin {
            modules(
                module {
                    single { locationLocalDataSource }
                    single<LocationDataSource> { locationDataSource }
                })
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun checkLocalDataSource() {
        runBlocking {
            val dataSource = LocationLocalDataSourceImpl()
            val location = LocationModel(23.0, 74.0)
            dataSource.setLocation(location)
            assert( dataSource.getLocation() == location)
        }
    }


    @Test
    fun checkLocationRepositoryCallCachingLocation() {
        runBlocking {
            val locationRepository = LocationRepositoryImpl()
            locationRepository.getLocation()
            assert(setLocationCalled)
        }
    }

    @Test
    fun checkLocationRepositoryCachingLocation() {
        hasCachedLocation = true
        runBlocking {
            val locationRepository = LocationRepositoryImpl()
            val location = locationRepository.getLocation()
            assert(location == cachedLocationModel)
        }
    }



    @Test
    fun checkLocationRepositoryParsedTrueLocation() {
        hasCachedLocation = false
        runBlocking {
            val locationRepository = LocationRepositoryImpl()
            val location = locationRepository.getLocation()
            assert(location == LocationModel(lat, lon))
        }
    }


}