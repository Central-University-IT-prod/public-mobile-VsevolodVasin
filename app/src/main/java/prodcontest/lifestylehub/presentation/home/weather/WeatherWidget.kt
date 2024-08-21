package prodcontest.lifestylehub.presentation.home.weather

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.java.KoinJavaComponent
import prodcontest.domain.weather.model.WeatherModel
import prodcontest.lifestylehub.R
import prodcontest.lifestylehub.presentation.skeletonWrapper.Skeleton
import prodcontest.lifestylehub.semantics.drawableId

@Composable
fun WeatherWidget(modifier: Modifier) {

    val vm : WeatherViewModel by KoinJavaComponent.inject(WeatherViewModel::class.java)
    ElevatedCard(
        modifier
            .height(180.dp)
            .fillMaxWidth(),
        colors = CardColors(
            MaterialTheme.colorScheme.secondaryContainer,
            MaterialTheme.colorScheme.onSecondaryContainer,
            MaterialTheme.colorScheme.secondaryContainer,
            MaterialTheme.colorScheme.secondaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        when (val weather = vm.weatherUiState) {
            is WeatherUiState.Success -> {
                WeatherSuccess(weatherModel = weather.weather)
            }

            is WeatherUiState.Error -> {
                Text(text = stringResource(id = R.string.weather_widget_error))
            }

            is WeatherUiState.Loading -> {
                WeatherSkeleton()
            }
        }
    }

}


@SuppressLint("DiscouragedApi")
@Composable
fun WeatherSuccess(weatherModel: WeatherModel) {
    val context = LocalContext.current
    val resource = context.resources.getIdentifier(weatherModel.weather.icon, "drawable", context.packageName)
    Row(Modifier.padding(16.dp)) {
       Column {
           Text(text = weatherModel.city, style = MaterialTheme.typography.headlineSmall.copy(textAlign = TextAlign.Center), modifier = Modifier.fillMaxWidth().testTag(WeatherWidgetTags.CITY))
           Spacer(modifier = Modifier.height(4.dp))
           Row(verticalAlignment = Alignment.CenterVertically) {
               Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                   Image(
                       modifier = Modifier.size(65.dp).testTag(WeatherWidgetTags.ICON).semantics { drawableId = resource },
                       painter= painterResource(id = resource),
                       contentDescription = null,
                   )
                   Text(text = weatherModel.weather.name, modifier = Modifier.fillMaxWidth().testTag(WeatherWidgetTags.WEATHER), style = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center))
               }
               Spacer(Modifier.weight(0.5f))
               Column(
                   horizontalAlignment = Alignment.CenterHorizontally,
                   verticalArrangement = Arrangement.Center
               ) {
                   Text(text = stringResource(id = R.string.weather_range, weatherModel.temperature.weatherMin, weatherModel.temperature.weatherMax), style = MaterialTheme.typography.bodyLarge, modifier = Modifier.testTag(WeatherWidgetTags.TEMPERATURE_RANGE))
                   Text(text = stringResource(id = R.string.temperature, weatherModel.temperature.real), style = MaterialTheme.typography.headlineLarge, modifier = Modifier.testTag(WeatherWidgetTags.TEMPERATURE))
                   Text(text = stringResource(id = R.string.temperature_feel, weatherModel.temperature.feel), modifier = Modifier.testTag(WeatherWidgetTags.TEMPERATURE_FEEL))
               }
           }
       }
    }
}

@Composable
fun WeatherSkeleton() {
    Row(Modifier.padding(16.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Skeleton(modifier = Modifier
                .padding(horizontal = 32.dp)
                .height(24.dp)
                .fillMaxWidth())
            Row(
                Modifier
                    .padding(top = 12.dp)
                    .fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Skeleton(modifier = Modifier.size(65.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    Skeleton(modifier = Modifier
                        .height(18.dp)
                        .width(70.dp)
                    )
                }
                Column(Modifier.padding(start = 24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Skeleton(modifier = Modifier
                        .height(13.dp)
                        .width(130.dp)
                    )
                    Skeleton(modifier = Modifier
                        .height(30.dp)
                        .width(120.dp)
                    )
                    Skeleton(modifier = Modifier
                        .height(12.dp)
                        .fillMaxWidth()
                    )
                }
            }
        }
    }
}

object WeatherWidgetTags {
    const val ICON = "WeatherWidgetTags:ICON"
    const val CITY = "WeatherWidgetTags:CITY"
    const val WEATHER = "WeatherWidgetTags:WEATHER"
    const val TEMPERATURE_RANGE = "WeatherWidgetTags:TEMPERATURE_RANGE"
    const val TEMPERATURE = "WeatherWidgetTags:TEMPERATURE"
    const val TEMPERATURE_FEEL = "WeatherWidgetTags:TEMPERATURE_FEEL"
}

