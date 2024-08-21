package prodcontest.lifestylehub.presentation.leisure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import prodcontest.domain.leisure.model.LeisureModel
import prodcontest.domain.leisure.repository.LeisureRepository

sealed interface LeisureListUiState {
    data class Success(val list: Flow<List<LeisureModel>>) : LeisureListUiState
    data object Error : LeisureListUiState
    data object Loading : LeisureListUiState
}

class LeisureViewModel : ViewModel()  {


    private val leisureRepository : LeisureRepository by inject(LeisureRepository::class.java)

    lateinit var leisureList : Flow<List<LeisureModel>>

    init {
        getLeisureList()
    }

    private fun getLeisureList() {
        leisureList = leisureRepository.getLeisureList()
    }

    fun saveLeisure(model : LeisureModel) {
        viewModelScope.launch {
            leisureRepository.addLeisure(
                model
            )
        }
    }
}