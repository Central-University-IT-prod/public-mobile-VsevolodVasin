package prodcontest.data.activity


import prodcontest.data.activity.response.ActivityResponseModel
import prodcontest.data.weather.response.WeatherResponseModel
import prodcontest.domain.activity.model.ActivityModel
import prodcontest.domain.weather.model.WeatherModel



internal fun ActivityResponseModel.mapToDomainModel() = ActivityModel(
    name=activity,
    type=type
)