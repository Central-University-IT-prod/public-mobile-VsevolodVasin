package prodcontest.lifestylehub.presentation.home.recommendation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.compose.BlackTransparent
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import prodcontest.domain.recommendation.model.RecommendationDetailsModel
import prodcontest.lifestylehub.presentation.leisure.addToLeisureBottomSheet.AddToLeisureDialog


@Composable
fun RecommendationDetailsScreen(id : String) {
    val vm = koinViewModel<RecommendationDetailsViewModel>()

    var showLeisureDialog by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        vm.getDetails(id)
    }
    val details = vm.recommendationUiState
    if (details is RecommendationDetailsUiState.Success) {
        Scaffold(floatingActionButton = {
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
        }) { paddingValues ->
           Box(Modifier.padding(paddingValues)) {
               LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                   item {
                       RecommendationDetailsTop(details.recommendation)
                       RecommendationDetailsCarousel(details.recommendation)
                       Text(
                           text = details.recommendation.description,
                           style = MaterialTheme.typography.bodyLarge,
                           modifier = Modifier
                               .padding(horizontal = 16.dp)
                               .padding(top = 16.dp)
                       )
                  }
                   if (details.recommendation.workingSegments!=null) {
                       recommendationWorkingHours(details.recommendation.workingSegments!!)
                   }
                   item {
                       if (details.recommendation.contactPhone != null) {
                           ContactCard(details.recommendation)
                       }
                   }
               }
           }
        }
        if (showLeisureDialog) {
            AddToLeisureDialog(
                details.recommendation,
                {
                    vm.saveLeisure(it)
                }
            ) {
                showLeisureDialog = false
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
}


fun LazyListScope.recommendationWorkingHours(workingSegments : List<RecommendationDetailsModel.WorkingSegment>) {
    item {
        Spacer(Modifier.height(16.dp))
    }
    items(workingSegments) {
        Text(
            it.name,
            style= MaterialTheme.typography.titleLarge.copy(textAlign = TextAlign.Start),
            modifier = Modifier.fillMaxWidth().padding(start=16.dp)
        )
        Text(
            it.hours,
            style= MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Start),
            modifier = Modifier.fillMaxWidth().padding(start=16.dp)
        )
    }
}

@Composable
fun RecommendationDetailsTop(recommendation : RecommendationDetailsModel) {
   Box {
       GlideImage(
           modifier = Modifier
               .fillMaxWidth()
               .wrapContentHeight(),
           imageModel = { recommendation.imageUrl },
           imageOptions = ImageOptions(
               contentScale = ContentScale.Fit,
               alignment = Alignment.Center,
               colorFilter = ColorFilter.tint(BlackTransparent, blendMode = BlendMode.Darken)
           )
       )

       Column(
           Modifier
               .fillMaxWidth()
               .padding(horizontal = 15.dp, vertical = 15.dp)) {
           Text(
               recommendation.name,
               style= MaterialTheme.typography.headlineLarge,
               color = MaterialTheme.colorScheme.onPrimary
           )
           Text(
               recommendation.address,
               style= MaterialTheme.typography.headlineSmall,
               color = MaterialTheme.colorScheme.onPrimary,
           )
       }
   }
}

@Composable
fun RecommendationDetailsCarousel(recommendation : RecommendationDetailsModel) {
    LazyRow(
        Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(top = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)) {
       items(recommendation.images) { image ->
           GlideImage(
               modifier = Modifier
                   .fillMaxHeight(),
               imageModel = { image },
               imageOptions = ImageOptions(
                   contentScale = ContentScale.Fit,
                   alignment = Alignment.Center
               )
           )
       }
    }
}

@Composable
fun ContactCard(recommendation: RecommendationDetailsModel) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${recommendation.contactPhone}"))
    val context = LocalContext.current

    ElevatedCard(
        Modifier
            .wrapContentSize()
            .padding(top = 32.dp)
            .clickable {
                startActivity(context, intent, null)
            },
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    ) {
             Text(
                 recommendation.contactFormattedPhone!!,
                 style= MaterialTheme.typography.headlineLarge,
                 color = MaterialTheme.colorScheme.onPrimary,
                 modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
             )
        }
    Spacer(Modifier.height(16.dp))
    }
