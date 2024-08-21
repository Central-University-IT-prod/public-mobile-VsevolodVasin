package prodcontest.auth.data.users.dbmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class UserDatabaseModel(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "gender") val gender     : String,
    @ColumnInfo(name = "name") val name       : String,
    @ColumnInfo(name = "email") val email      : String,
    @ColumnInfo(name = "login") val login      : String,
    @ColumnInfo(name = "phone") val phone      : String,
    @ColumnInfo(name = "picture") val picture    : String?    = null,
    @ColumnInfo(name = "password") val password    : String,
)