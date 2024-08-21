package prodcontest.data.location.datasource

import prodcontest.domain.location.model.LocationModel

interface LocationLocalDataSource {
    fun getLocation() : LocationModel?

    fun setLocation(location: LocationModel)
}