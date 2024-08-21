package prodcontest.auth.data.users.datasource.local

import org.koin.java.KoinJavaComponent
import prodcontest.auth.data.users.dbmodel.UserDatabaseModel

class UsersLocalDataSource() {

    private val dao : UsersDao by KoinJavaComponent.inject(UsersDao::class.java)

    suspend fun addUser(user: UserDatabaseModel) : Int {
        return dao.insertUser(user).toInt()
    }

    suspend fun getUser(login: String): UserDatabaseModel? = dao.getUser(login)

}