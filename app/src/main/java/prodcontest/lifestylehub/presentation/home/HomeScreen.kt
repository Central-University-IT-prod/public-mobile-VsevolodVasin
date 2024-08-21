package prodcontest.lifestylehub.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import prodcontest.lifestylehub.R
import prodcontest.lifestylehub.presentation.home.activity.ActivityWidget
import prodcontest.lifestylehub.presentation.home.map.Map
import prodcontest.lifestylehub.presentation.home.recommendation.RecommendationsWidget
import prodcontest.lifestylehub.presentation.home.weather.WeatherWidget
import prodcontest.lifestylehub.presentation.navigation.Destinations

@Composable
fun HomeScreen(navController: NavController) {
    Column {
        LazyColumn {
            item {
                WeatherWidget(modifier = Modifier.padding(16.dp))
            }
            item {
                ActivityWidget(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
            }
        }
        var mapMode by remember {  mutableStateOf(false) }
        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = stringResource(id = R.string.map_mode))
            Spacer(Modifier.width(8.dp))
            Switch(checked = mapMode, onCheckedChange = { mapMode = !mapMode })
        }
       if (mapMode) {
           Map(modifier = Modifier) {
               navController.navigate("${Destinations.PlaceDetails.route}?recommendationId=$it")
           }
       } else {
           RecommendationsWidget(
               modifier = Modifier
               .padding(horizontal = 16.dp),
               navController=navController
           )
       }
    }
}