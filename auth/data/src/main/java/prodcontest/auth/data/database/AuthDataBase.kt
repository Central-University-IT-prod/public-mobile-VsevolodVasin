package prodcontest.auth.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import prodcontest.auth.data.users.datasource.local.UsersDao
import prodcontest.auth.data.users.dbmodel.UserDatabaseModel

@Database(
    entities = [UserDatabaseModel::class],
    version = 1
)
abstract class AuthDataBase: RoomDatabase() {

    abstract val usersDao: UsersDao

    companion object {
        const val DATABASE_NAME = "auth_db"
    }
}