 package prodcontest.data.di

import androidx.room.Room
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import prodcontest.data.activity.datasource.ActivityRemoteDataSource
import prodcontest.data.activity.repository.ActivityRepositoryImpl
import prodcontest.data.database.AppDataBase
import prodcontest.data.leisure.datasource.LeisureDao
import prodcontest.data.leisure.datasource.LeisureLocalDataSource
import prodcontest.data.leisure.repository.LeisureRepositoryImpl
import prodcontest.data.location.repository.LocationRepositoryImpl
import prodcontest.data.location.datasource.LocationDataSource
import prodcontest.data.location.datasource.LocationDataSourceImpl
import prodcontest.data.location.datasource.LocationLocalDataSource
import prodcontest.data.location.datasource.LocationLocalDataSourceImpl
import prodcontest.data.recommendations.datasource.RecommendationsRemoteDataSource
import prodcontest.data.recommendations.datasource.local.RecommendationsDao
import prodcontest.data.recommendations.datasource.local.RecommendationsLocalDataSource
import prodcontest.data.recommendations.repository.RecommendationsRepositoryImpl
import prodcontest.data.weather.datasource.WeatherRemoteDataSource
import prodcontest.data.weather.datasource.WeatherRemoteDataSourceImpl
import prodcontest.data.weather.repository.WeatherRepositoryImpl
import prodcontest.domain.activity.repository.ActivityRepository
import prodcontest.domain.leisure.repository.LeisureRepository
import prodcontest.domain.location.repository.LocationRepository
import prodcontest.domain.recommendation.repository.RecommendationsRepository
import prodcontest.domain.weather.repository.WeatherRepository
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

    single<WeatherRemoteDataSource> {
        WeatherRemoteDataSourceImpl()
    }

    single<LocationDataSource> {
        LocationDataSourceImpl(androidContext())
    }

    single<LocationLocalDataSource> {
        LocationLocalDataSourceImpl()
    }



    single<LocationRepository> {
        LocationRepositoryImpl()
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl()
    }

    single<RecommendationsRemoteDataSource> {
        RecommendationsRemoteDataSource()
    }

    single<RecommendationsRepository> {
        RecommendationsRepositoryImpl()
    }

    single<AppDataBase> {
        Room.databaseBuilder(
            androidApplication(),
            AppDataBase::class.java,
            AppDataBase.DATABASE_NAME
        ).build()
    }

    single<RecommendationsDao> {
        val appDataBase : AppDataBase = get()
        appDataBase.recommendationsDao
    }

    single<RecommendationsLocalDataSource> {
        RecommendationsLocalDataSource()
    }

    single<LeisureDao> {
        val appDataBase : AppDataBase = get()
        appDataBase.leisureDao
    }

    single<LeisureLocalDataSource> {
        LeisureLocalDataSource()
    }

    single<LeisureRepository> {
        LeisureRepositoryImpl()
    }

    single<ActivityRepository> {
        ActivityRepositoryImpl()
    }

    single<ActivityRemoteDataSource> {
        ActivityRemoteDataSource()
    }

}

