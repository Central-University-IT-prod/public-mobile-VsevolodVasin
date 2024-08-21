package prodcontest.auth.domain.users.models

data class UserModel (
   var gender     : String,
   var name       : String,
   var email      : String,
   var login      : String,
   var phone      : String,
   var id         : Int,
   var picture    : String?    = null,
)