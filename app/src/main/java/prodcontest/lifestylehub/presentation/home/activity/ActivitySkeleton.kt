package prodcontest.lifestylehub.presentation.home.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import prodcontest.lifestylehub.presentation.skeletonWrapper.Skeleton

@Composable
fun ActivitySkeleton() {
    Row(Modifier.padding(16.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Skeleton(modifier = Modifier
                .padding(horizontal = 32.dp)
                .height(24.dp)
                .fillMaxWidth())
        }
    }
}
