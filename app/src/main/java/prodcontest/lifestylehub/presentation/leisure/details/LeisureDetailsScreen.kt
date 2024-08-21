package prodcontest.lifestylehub.presentation.leisure.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import prodcontest.lifestylehub.R
import prodcontest.lifestylehub.presentation.navigation.Destinations
import prodcontest.lifestylehub.utils.addToCalendar

@Composable
fun LeisureDetailsScreen(navController : NavController, id : Int) {
    val vm = koinViewModel<LeisureDetailsViewModel>()
    val context = LocalContext.current
    if (vm.leisureListUiState is LeisureUiState.Loading) {
        vm.getLeisure(id)
    }
    when(vm.leisureListUiState) {
        is LeisureUiState.Success -> {
            Column(
                Modifier.padding(top=16.dp).padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val leisureModel = (vm.leisureListUiState as LeisureUiState.Success).leisure
                Text(leisureModel.name, style = MaterialTheme.typography.headlineMedium, modifier=Modifier.fillMaxWidth())
                Text(leisureModel.date, style = MaterialTheme.typography.bodyLarge, modifier=Modifier.fillMaxWidth())
                Text(leisureModel.description, style = MaterialTheme.typography.bodyLarge, modifier=Modifier.fillMaxWidth())

                if (leisureModel.recommendation!=null) {
                    OutlinedButton(
                        onClick = {
                            navController.navigate("${Destinations.PlaceDetails.route}?recommendationId=${leisureModel.recommendation}")
                        }
                    ) {
                        Text(
                            stringResource(id = R.string.to_place),
                            style= MaterialTheme.typography.titleLarge
                        )
                    }
                }

                OutlinedButton(
                    onClick = {
                        leisureModel.addToCalendar(context)
                    }
                ) {
                    Text(
                        stringResource(id = R.string.add_to_calendar),
                        style= MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
        is LeisureUiState.Loading -> {

        }
        is LeisureUiState.Error -> {

        }
    }

}

