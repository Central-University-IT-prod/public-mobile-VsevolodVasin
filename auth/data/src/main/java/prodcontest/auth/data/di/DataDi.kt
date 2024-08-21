package prodcontest.auth.data.di

import androidx.room.Room
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import prodcontest.auth.data.database.AuthDataBase
import prodcontest.auth.data.users.datasource.local.UsersDao
import prodcontest.auth.data.users.datasource.local.UsersLocalDataSource
import prodcontest.auth.data.users.datasource.remote.UsersRemoteDataSource
import prodcontest.auth.data.users.repository.UserRepositoryImpl
import prodcontest.auth.domain.users.repository.UserRepository
import timber.log.Timber

val dataModule = module {

    single<HttpClient> {
        HttpClient() {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys=true
                })
            }
            install(Logging) {
                logger = object: Logger {
                    override fun log(message: String) {
                        Timber.v(message)
                    }
                }
                level = LogLevel.BODY
            }
        }
    }

    single<UsersRemoteDataSource> {
        UsersRemoteDataSource()
    }

    single<AuthDataBase> {
        Room.databaseBuilder(
            androidApplication(),
            AuthDataBase::class.java,
            AuthDataBase.DATABASE_NAME
        ).build()
    }

    single<UsersDao> {
        val appDataBase : AuthDataBase = get()
        appDataBase.usersDao
    }


    single<UsersLocalDataSource> {
        UsersLocalDataSource()
    }

    single<UserRepository> {
        UserRepositoryImpl()
    }
}

