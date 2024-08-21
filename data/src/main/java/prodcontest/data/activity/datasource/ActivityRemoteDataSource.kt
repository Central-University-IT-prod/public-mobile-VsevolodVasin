package prodcontest.data.activity.datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import org.koin.java.KoinJavaComponent.inject
import prodcontest.data.activity.response.ActivityResponseModel


internal class ActivityRemoteDataSource {

    private val httpClient : HttpClient by inject(HttpClient::class.java)

    suspend fun getActivity(easy : Boolean) = httpClient.get {
        url(GET_CURRENT_WEATHER_PATH)
        parameter(MIN_ACCESSIBILITY_PARAM, if (easy) { EASY_ACCESSIBILITY } else { MIN_ACCESSIBILITY })
        parameter(MAX_ACCESSIBILITY_PARAM, MAX_ACCESSIBILITY)
    }.body<ActivityResponseModel>()

    companion object {
        private const val ROOT = "https://www.boredapi.com/api"
        private const val GET_CURRENT_WEATHER_PATH = "$ROOT/activity/"

        private const val MIN_ACCESSIBILITY_PARAM = "minaccessibility"
        private const val EASY_ACCESSIBILITY = 0.5
        private const val MIN_ACCESSIBILITY = 0
        private const val MAX_ACCESSIBILITY_PARAM = "maxaccessibility"
        private const val MAX_ACCESSIBILITY = 1
    }
}