package prodcontest.data.activity.repository

import org.koin.java.KoinJavaComponent
import prodcontest.data.activity.datasource.ActivityRemoteDataSource
import prodcontest.data.activity.mapToDomainModel
import prodcontest.domain.activity.model.ActivityModel
import prodcontest.domain.activity.repository.ActivityRepository

class ActivityRepositoryImpl : ActivityRepository {

    private val remoteDataSource : ActivityRemoteDataSource by KoinJavaComponent.inject(
        ActivityRemoteDataSource::class.java
    )

    override suspend fun getActivity(easy : Boolean): ActivityModel = remoteDataSource.getActivity(easy).mapToDomainModel()
}