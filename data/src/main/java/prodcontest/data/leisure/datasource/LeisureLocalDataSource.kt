package prodcontest.data.leisure.datasource

import kotlinx.coroutines.flow.Flow
import org.koin.java.KoinJavaComponent
import prodcontest.data.leisure.dbmodel.LeisureDatabaseModel

class LeisureLocalDataSource() {

    private val dao : LeisureDao by KoinJavaComponent.inject(LeisureDao::class.java)

    fun getLeisureList() : Flow<List<LeisureDatabaseModel>> = dao.getLeisureList()

    suspend fun addLeisure(leisureDatabaseModel: LeisureDatabaseModel) {
        return dao.insertLeisure(leisureDatabaseModel)
    }

    suspend fun removeLeisure(uid: Int) {
        return dao.removeLeisure(uid)
    }

    suspend fun getLeisure(uid: Int): LeisureDatabaseModel = dao.getLeisure(uid)

}