package prodcontest.lifestylehub.presentation.home.recommendation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import prodcontest.domain.recommendation.model.RecommendationModel

@Composable
fun RecommendationCard(recommendationModel: RecommendationModel) {

    ElevatedCard(
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