package prodcontest.lifestylehub.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import prodcontest.lifestylehub.presentation.home.recommendation.RecommendationsWidget
import prodcontest.lifestylehub.presentation.home.weather.WeatherWidget

@Composable
fun HomeScreen(navController: NavController) {
    Column() {
        LazyColumn {
            item {
                WeatherWidget(modifier = Modifier.padding(16.dp))
            }
        }
        RecommendationsWidget(modifier = Modifier.padding(16.dp).zIndex(2f).background(Color.Transparent), navController=navController)
    }
}