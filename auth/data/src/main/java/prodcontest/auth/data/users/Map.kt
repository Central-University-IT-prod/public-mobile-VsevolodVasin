package prodcontest.auth.data.users

import prodcontest.auth.data.users.dbmodel.UserDatabaseModel
import prodcontest.auth.data.users.response.UserResponseModel
import prodcontest.auth.domain.users.models.UserModel

fun UserDatabaseModel.mapToDomainModel(id : Int? = null) : UserModel {
    return UserModel(
        gender = gender,
        name = name,
        email = email,
        login = login,
        phone = phone,
        id = uid?: id ?: 0,
        picture = picture
    )
}

fun UserResponseModel.User.toDataBaseUser(password : String) : UserDatabaseModel {
    return UserDatabaseModel(
        gender = gender,
        name = name.title,
        email = email,
        login = login.username,
        phone = phone,
        picture = picture?.thumbnail,
        password = password
    )
}