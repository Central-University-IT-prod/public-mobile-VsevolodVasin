package prodcontest.lifestylehub.presentation.leisure.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import prodcontest.domain.leisure.model.LeisureModel
import prodcontest.domain.leisure.repository.LeisureRepository

sealed interface LeisureUiState {
    data class Success(val leisure: LeisureModel) : LeisureUiState
    data object Error : LeisureUiState
    data object Loading : LeisureUiState
}

class LeisureDetailsViewModel : ViewModel()  {


    private val leisureRepository : LeisureRepository by inject(LeisureRepository::class.java)

    var leisureListUiState: LeisureUiState by mutableStateOf(LeisureUiState.Loading)
        private set

     fun getLeisure(id : Int) {
        viewModelScope.launch {
            leisureListUiState = LeisureUiState.Loading
            leisureListUiState = try {
                LeisureUiState.Success(leisureRepository.getLeisure(id))
            } catch (_ : Exception) {
                LeisureUiState.Error
            }
        }
    }
}