package prodcontest.lifestylehub.presentation.home.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import prodcontest.domain.activity.model.ActivityModel

@Composable
fun ActivitySuccess(activityModel: ActivityModel) {
    Row(Modifier.padding(16.dp)) {
       Column {
           Text(
               text = activityModel.name,
               style = MaterialTheme.typography.headlineSmall.copy(textAlign = TextAlign.Center),
               modifier = Modifier.fillMaxWidth()
           )
       }
    }
}


