package prodcontest.auth.data.users.repository

import org.koin.java.KoinJavaComponent
import prodcontest.auth.data.users.datasource.local.UsersLocalDataSource
import prodcontest.auth.data.users.datasource.remote.UsersRemoteDataSource
import prodcontest.auth.data.users.mapToDomainModel
import prodcontest.auth.data.users.toDataBaseUser
import prodcontest.auth.data.users.utils.hashPassword
import prodcontest.auth.domain.users.models.UserModel
import prodcontest.auth.domain.users.repository.UserRepository

class UserRepositoryImpl : UserRepository {

    private val usersLocalDataSource : UsersLocalDataSource by KoinJavaComponent.inject(
        UsersLocalDataSource::class.java
    )

    private val usersRemoteDataSource : UsersRemoteDataSource by KoinJavaComponent.inject(
        UsersRemoteDataSource::class.java
    )

    override suspend fun createUser(password: String): UserModel {
        val user = usersRemoteDataSource.getUser().results.first()
        val databaseUser = user.toDataBaseUser(hashPassword(password))
        val newId = usersLocalDataSource.addUser(
            databaseUser
        )

        return databaseUser.mapToDomainModel(newId)

    }

    override suspend fun getUser(login: String, password: String): UserModel? {
        val user = usersLocalDataSource.getUser(login)
        if (user==null) return null
        return if (user.password == hashPassword(password)) {
            user.mapToDomainModel()
        } else {
            null
        }
    }

}