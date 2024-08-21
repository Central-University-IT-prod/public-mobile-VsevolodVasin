package prodcontest.lifestylehub.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import prodcontest.lifestylehub.presentation.auth.AuthViewModel
import prodcontest.lifestylehub.presentation.home.activity.ActivityViewModel
import prodcontest.lifestylehub.presentation.home.map.MapViewModel
import prodcontest.lifestylehub.presentation.home.recommendation.RecommendationsViewModel
import prodcontest.lifestylehub.presentation.home.recommendation.details.RecommendationDetailsViewModel
import prodcontest.lifestylehub.presentation.home.weather.WeatherViewModel
import prodcontest.lifestylehub.presentation.leisure.LeisureViewModel
import prodcontest.lifestylehub.presentation.leisure.details.LeisureDetailsViewModel

val presentationModule = module {
    single { WeatherViewModel() }
    single { RecommendationsViewModel() }
    viewModel { RecommendationDetailsViewModel() }
    viewModel { LeisureViewModel() }
    viewModel { LeisureDetailsViewModel() }
    viewModel { AuthViewModel() }
    viewModel { MapViewModel() }
    viewModel { ActivityViewModel() }
}