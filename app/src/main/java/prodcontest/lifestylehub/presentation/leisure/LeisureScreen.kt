package prodcontest.lifestylehub.presentation.leisure

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import prodcontest.domain.leisure.model.LeisureModel
import prodcontest.lifestylehub.presentation.leisure.addToLeisureBottomSheet.AddToLeisureDialog
import prodcontest.lifestylehub.presentation.navigation.Destinations

@Composable
fun LeisureScreen(navController : NavController) {
    val vm = koinViewModel<LeisureViewModel>()
    val coroutineScope = rememberCoroutineScope()
    var showLeisureDialog by remember { mutableStateOf(false) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                coroutineScope.launch {
                    showLeisureDialog = true
                }
            }) {
                Icon(
                    Icons.Outlined.Add,
                    contentDescription = null
                )
            }
        }
    ) { paddingValues ->

        val leisureList by vm.leisureList.collectAsState(
            initial = emptyList()
        )
        LazyColumn(
            Modifier
                .padding(paddingValues)
                .padding(top = 16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {

            items(leisureList) {
                LeisureCard(it, navController)
            }
        }
    }

    if (showLeisureDialog) {
        AddToLeisureDialog(add = {
            vm.saveLeisure(it)
        }) {
            showLeisureDialog = false
        }
    }
}

@Composable
fun LeisureCard(leisureModel: LeisureModel, navController: NavController) {
    ElevatedCard(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable {
                navController.navigate("${Destinations.LeisureDetails.route}?leisureId=${leisureModel.id}")
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    ) {
        Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Text(leisureModel.name, style = MaterialTheme.typography.headlineMedium)
            Text(leisureModel.date, style = MaterialTheme.typography.bodyLarge)
        }
    }
}