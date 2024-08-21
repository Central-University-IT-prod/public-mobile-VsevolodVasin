package prodcontest.lifestylehub.presentation.home.activity

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel
import prodcontest.lifestylehub.R


@Composable
fun ActivityWidget(modifier: Modifier) {
    val vm = koinViewModel<ActivityViewModel>()
    Card(
        modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        colors = CardColors(
            MaterialTheme.colorScheme.primaryContainer,
            MaterialTheme.colorScheme.onPrimaryContainer,
            MaterialTheme.colorScheme.primaryContainer,
            MaterialTheme.colorScheme.primaryContainer
        ),
    ) {
        when (val activity = vm.activityUiState) {
            is ActivityUiState.Success -> {
                ActivitySuccess(activityModel = activity.activity)
            }

            is ActivityUiState.Error -> {
                Text(text = stringResource(id = R.string.weather_widget_error))
            }

            is ActivityUiState.Loading -> {
                ActivitySkeleton()
            }
        }
    }

}
