package prodcontest.lifestylehub.presentation.home.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent
import prodcontest.domain.activity.model.ActivityModel
import prodcontest.domain.activity.repository.ActivityRepository

sealed interface ActivityUiState {
    data class Success(val activity: ActivityModel) : ActivityUiState
    data object Error : ActivityUiState
    data object Loading : ActivityUiState
}

class ActivityViewModel : ViewModel()  {

    private val activityRepository : ActivityRepository by KoinJavaComponent.inject(
        ActivityRepository::class.java
    )

    var activityUiState : ActivityUiState by mutableStateOf(ActivityUiState.Loading)
        private set


    init {
        getActivity()
    }

    private fun getActivity() {
        viewModelScope.launch {
            activityUiState = ActivityUiState.Loading
            activityUiState = try {
                ActivityUiState.Success(activityRepository.getActivity(easy = true))
            } catch (e : Exception) {
                ActivityUiState.Error
            }
        }
    }

}