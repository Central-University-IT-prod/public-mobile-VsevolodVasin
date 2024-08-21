package prodcontest.data.location.datasource

import prodcontest.domain.location.model.LocationModel

class LocationLocalDataSourceImpl() : LocationLocalDataSource {

    private var location : LocationModel? = null

    override fun getLocation() = location

    override fun setLocation(location: LocationModel) { this.location = location }


}