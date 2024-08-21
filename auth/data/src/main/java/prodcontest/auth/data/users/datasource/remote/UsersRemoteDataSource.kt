package prodcontest.auth.data.users.datasource.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import org.koin.java.KoinJavaComponent.inject
import prodcontest.auth.data.users.response.UserResponseModel


internal class UsersRemoteDataSource {

    private val httpClient : HttpClient by inject(HttpClient::class.java)

    suspend fun getUser() = httpClient.get {
        url(GET_USERS_PATH)
    }.body<UserResponseModel>()

    companion object {
        private const val ROOT = "https://randomuser.me"
        private const val GET_USERS_PATH = "$ROOT/api/"
    }
}