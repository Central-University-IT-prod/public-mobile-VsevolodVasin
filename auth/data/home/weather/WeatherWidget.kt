package prodcontest.lifestylehub.presentation.home.weather

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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.compose.koinViewModel
import prodcontest.domain.weather.model.WeatherModel
import prodcontest.lifestylehub.R
import prodcontest.lifestylehub.presentation.skeletonWrapper.Skeleton


@Composable
fun WeatherWidget(modifier: Modifier) {
    val vm = koinViewModel<WeatherViewModel>()
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


@Composable
fun WeatherSuccess(weatherModel: WeatherModel) {
    val context = LocalContext.current
    val resource = context.resources.getIdentifier(weatherModel.weather.icon, "drawable", context.packageName)
    Row(Modifier.padding(16.dp)) {
       Column {
           Text(text = weatherModel.city, style = MaterialTheme.typography.headlineSmall.copy(textAlign = TextAlign.Center), modifier = Modifier.fillMaxWidth())
           Spacer(modifier = Modifier.height(8.dp))
           Row(verticalAlignment = Alignment.CenterVertically) {
               Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                   Image(
                       modifier = Modifier.size(70.dp),
                       painter= painterResource(id = resource),
                       contentDescription = null,
                   )
                   Text(text = weatherModel.weather.name, modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center))
               }
               Spacer(Modifier.weight(0.5f))
               Column(
                   horizontalAlignment = Alignment.CenterHorizontally,
                   verticalArrangement = Arrangement.Center
               ) {
                   Text(text = stringResource(id = R.string.weather_range, weatherModel.temperature.weatherMin, weatherModel.temperature.weatherMax), style = MaterialTheme.typography.bodyLarge)
                   Text(text = stringResource(id = R.string.temperature, weatherModel.temperature.real), style = MaterialTheme.typography.headlineLarge)
                   Text(text = stringResource(id = R.string.temperature_feel, weatherModel.temperature.feel))
               }
           }
       }
    }
}

@Composable
fun WeatherModelPreview() {
    WeatherSuccess(weatherModel = WeatherModel(temperature = WeatherModel.Temperature(32.0,32.0,32.0,32.0), city = "novokuznetsk", weather = WeatherModel.Weather("weather_03d", "переменная облачность")))
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
                    .padding(top = 16.dp)
                    .fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Skeleton(modifier = Modifier.size(80.dp))
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

