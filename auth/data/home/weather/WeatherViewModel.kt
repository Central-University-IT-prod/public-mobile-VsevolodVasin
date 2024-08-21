package prodcontest.lifestylehub.presentation.home.weather

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import prodcontest.domain.weather.model.WeatherModel
import prodcontest.domain.weather.repository.WeatherRepository
import timber.log.Timber

sealed interface WeatherUiState {
    data class Success(val weather: WeatherModel) : WeatherUiState
    data object Error : WeatherUiState
    data object Loading : WeatherUiState
}

class WeatherViewModel : ViewModel()  {

    private val weatherRepository : WeatherRepository by inject(WeatherRepository::class.java)

    var weatherUiState: WeatherUiState by mutableStateOf(WeatherUiState.Loading)
        private set

    init {
        Timber.i("try get weather")
        getWeather()
    }

    private fun getWeather() {
        viewModelScope.launch {
            weatherUiState = WeatherUiState.Loading
            weatherUiState = try {
                WeatherUiState.Success(weatherRepository.getWeather())
            } catch (_ : Exception) {
                WeatherUiState.Error
            }
        }
    }

}