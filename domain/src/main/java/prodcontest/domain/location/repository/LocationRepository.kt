package prodcontest.domain.location.repository

import prodcontest.domain.location.model.LocationModel

interface LocationRepository {
    suspend fun getLocation(): LocationModel
}