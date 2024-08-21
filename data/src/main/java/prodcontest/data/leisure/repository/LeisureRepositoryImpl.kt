package prodcontest.data.leisure.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.java.KoinJavaComponent
import prodcontest.data.leisure.datasource.LeisureLocalDataSource
import prodcontest.data.leisure.dbmodel.LeisureDatabaseModel
import prodcontest.data.leisure.mapToDomainModel
import prodcontest.data.leisure.mapToDomainModels
import prodcontest.domain.leisure.model.LeisureModel
import prodcontest.domain.leisure.repository.LeisureRepository
import prodcontest.domain.location.repository.LocationRepository

class LeisureRepositoryImpl : LeisureRepository{

    private val leisureLocalDataSource : LeisureLocalDataSource by KoinJavaComponent.inject(
        LeisureLocalDataSource::class.java
    )

    override fun getLeisureList(): Flow<List<LeisureModel>> = leisureLocalDataSource.getLeisureList().map { leisureList -> leisureList.mapToDomainModels() }
    override suspend fun getLeisure(id: Int): LeisureModel =   leisureLocalDataSource.getLeisure(id).mapToDomainModel()

    override suspend fun addLeisure(leisureModel: LeisureModel) {
        leisureLocalDataSource.addLeisure(LeisureDatabaseModel(
            name = leisureModel.name,
            description = leisureModel.description,
            date = leisureModel.date,
            recommendation = leisureModel.recommendation
        ))
    }

    override suspend fun removeLeisure(uid: Int) {
        leisureLocalDataSource.removeLeisure(uid)
    }
}