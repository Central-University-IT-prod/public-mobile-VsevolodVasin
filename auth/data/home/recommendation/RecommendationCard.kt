package prodcontest.lifestylehub.presentation.home.recommendation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import org.koin.java.KoinJavaComponent
import prodcontest.domain.location.repository.LocationRepository
import prodcontest.domain.recommendation.model.RecommendationModel
import prodcontest.lifestylehub.presentation.navigation.Destinations

@Composable
fun RecommendationCard(recommendationModel: RecommendationModel, navController: NavController) {

    ElevatedCard(
        Modifier.clickable {
            navController.navigate("${Destinations.PlaceDetails.route}?recommendationId=${recommendationModel.id}")
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    ) {
        Column {
            GlideImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                imageModel = { recommendationModel.imageUrl },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                )
            )
            Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(recommendationModel.name, style= MaterialTheme.typography.headlineMedium)
                Text(recommendationModel.address, style= MaterialTheme.typography.bodyLarge)
                LazyRow {
                    items(recommendationModel.category) {
                        Text(it.name, style= MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}