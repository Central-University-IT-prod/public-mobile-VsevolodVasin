package prodcontest.lifestylehub.presentation.home.recommendation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecommendationsWidget(modifier : Modifier, navController : NavController) {
    val vm = koinViewModel<RecommendationsViewModel>()

    val items = vm.flow.collectAsLazyPagingItems()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = items.loadState.refresh == LoadState.Loading,
        onRefresh = { items.refresh() }
    )

    LazyColumn(modifier.pullRefresh(pullRefreshState), verticalArrangement = Arrangement.spacedBy(16.dp)) {

        if (items.loadState.refresh == LoadState.Loading) {
            item { LoadingIndicator() }
        }

        items(items.itemCount) {
            if(items[it]!=null) {
                val item = items[it]
                Box(Modifier.fillMaxWidth().wrapContentHeight().clickable {
                   if (item?.navPath!=null) navController.navigate(item.navPath)
                }) {
                    item!!.content()
                }
            }
        }

        if (items.loadState.append is LoadState.Loading)  {
            item { LoadingIndicator() }
        }
    }
}

@Composable
fun LoadingIndicator() {
    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Spacer(Modifier.weight(1f))
        CircularProgressIndicator()
        Spacer(Modifier.weight(1f))
    }
}



