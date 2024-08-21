package prodcontest.auth.domain.users.repository

import prodcontest.auth.domain.users.models.UserModel

interface UserRepository {
    suspend fun createUser(password : String): UserModel

    suspend fun getUser(login : String, password: String): UserModel?

}