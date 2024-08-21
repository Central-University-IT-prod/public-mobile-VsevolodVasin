package prodcontest.data.location.datasource

import prodcontest.domain.location.model.LocationModel

interface LocationDataSource {
    suspend fun getLocation() : LocationModel
}