package prodcontest.auth.data.users.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import prodcontest.auth.data.users.dbmodel.UserDatabaseModel

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: UserDatabaseModel) : Long

    @Query("SELECT * FROM users WHERE login = :login")
    suspend fun getUser(login : String) : UserDatabaseModel?

}