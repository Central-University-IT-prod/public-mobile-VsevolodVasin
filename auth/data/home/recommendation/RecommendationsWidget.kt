package prodcontest.lifestylehub.presentation.home.recommendation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel


@Composable
fun RecommendationsWidget(modifier : Modifier, navController : NavController) {
    val vm = koinViewModel<RecommendationsViewModel>()

    if (vm.flow == null) {
        vm.setupPager(navController)
    }


    val items = vm.flow!!.collectAsLazyPagingItems()

    LazyColumn(modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {

        if (items.loadState.refresh == LoadState.Loading) {
            item {
                Text(text = "загрузка")
            }
        }

        items(items.itemCount) {
            if(items[it]!=null) {
                items[it]!!.content()
            }

        }

    }
}



