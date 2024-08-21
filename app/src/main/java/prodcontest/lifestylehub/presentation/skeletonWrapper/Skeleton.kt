package prodcontest.lifestylehub.presentation.skeletonWrapper

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun Skeleton(modifier: Modifier, shape : Shape = RoundedCornerShape(4.dp)) {
    val color = remember { Animatable(Color.LightGray) }
    var animationToggle by remember { mutableStateOf(true) }
    LaunchedEffect(animationToggle) {
        animationToggle = if (animationToggle) {
            color.animateTo(Color.Gray, animationSpec = tween(400))
            false
        } else {
            color.animateTo(Color.LightGray, animationSpec = tween(400))
            true
        }
    }

    Box(modifier.background(color.value, shape=shape))
}