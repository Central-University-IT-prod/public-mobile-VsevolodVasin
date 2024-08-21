package prodcontest.data.location.datasource

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Tasks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import prodcontest.domain.location.model.LocationModel

class LocationDataSourceImpl(context : Context) : LocationDataSource {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override suspend fun getLocation() = withContext(Dispatchers.IO) {
        val location = Tasks.await(fusedLocationClient.lastLocation)
        return@withContext LocationModel(location.latitude, location.longitude)
    }

}