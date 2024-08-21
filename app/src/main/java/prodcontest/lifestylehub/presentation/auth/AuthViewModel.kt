package prodcontest.lifestylehub.presentation.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import prodcontest.auth.domain.users.models.UserModel
import prodcontest.auth.domain.users.repository.UserRepository

sealed interface UserUiState {
    data class Authed(val user: UserModel) : UserUiState
    data object NotAuthed : UserUiState
}

class AuthViewModel : ViewModel()  {

    private val userRepository : UserRepository by inject(UserRepository::class.java)

    var user: UserUiState by mutableStateOf(UserUiState.NotAuthed)
        private set

    fun loginUser(login : String, password : String) {
        viewModelScope.launch {
            val userResult = userRepository.getUser(login, password)
            if (userResult!=null) {
                user = UserUiState.Authed(userResult)
            }
        }
    }

    fun registerUser(password: String) {
        viewModelScope.launch {
            val userResult = userRepository.createUser(password)
            user = UserUiState.Authed(userResult)
        }
    }
}