package prodcontest.lifestylehub.presentation.home.map

import android.view.LayoutInflater
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.TextStyle
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import org.koin.androidx.compose.koinViewModel
import prodcontest.lifestylehub.R

@Composable
fun Map(modifier : Modifier, navigateToRecommendation: (id : String) -> Unit) {
    val vm = koinViewModel<MapViewModel>()
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val location = vm.locationUiState
    val recommendations = vm.recommendationsUiState

    var observer : LifecycleEventObserver? by remember {
        mutableStateOf(null)
    }

    val cameraListener = remember {
        CameraListener { map, cameraPosition, cameraUpdateReason, finished ->
            if (cameraPosition.zoom > 9 && finished && vm.checkNeedLoad(cameraPosition.target)) {
                 vm.getRecommendations(cameraPosition.target.latitude, cameraPosition.target.longitude)
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        onDispose {
            if (observer!=null) {
                lifecycleOwner.lifecycle.removeObserver(observer!!)
            }
        }
    }

    Box {
        AndroidView(modifier=modifier, factory = { context ->
            val layoutInflater = LayoutInflater.from(context)
            val frame = layoutInflater.inflate(R.layout.map, null, false)
            val map = frame.findViewById<MapView>(R.id.mapview)
            observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_START ) {
                    map.onStart()
                }

                if (event == Lifecycle.Event.ON_STOP ) {
                    map.onStop()
                }
            }

            lifecycleOwner.lifecycle.addObserver(observer!!)

            map.mapWindow.map.addCameraListener(cameraListener)

            if (location is LocationUiState.Success) {
                map.mapWindow.map.move(
                    CameraPosition(Point(location.location.latitude, location.location.longitude), 17f, 0f, 30.0f)
                )
            }

            return@AndroidView frame
        }) { frame ->
            val map = frame.findViewById<MapView>(R.id.mapview)

            if (recommendations is RecommendationsUiState.Success) {

                map.mapWindow.map.mapObjects.clear()
                val imageProvider = ImageProvider.fromResource(context, R.drawable.placemark_icon)

                for (recommendation in recommendations.recommendations) {
                    val markTapListener = MapObjectTapListener { _, point ->
                        navigateToRecommendation(recommendation.id)
                        true
                    }

                    map.mapWindow.map.mapObjects.addPlacemark().apply {
                        geometry = Point(recommendation.coordinates.lat, recommendation.coordinates.long)
                        setIcon(imageProvider)
                        setText(
                            recommendation.name,
                            TextStyle().apply {
                                size = 10f
                                placement = TextStyle.Placement.BOTTOM
                                offset = 5f
                            },
                        )
                        addTapListener(markTapListener)
                    }
                }
            }
        }

        if (location is LocationUiState.Loading || recommendations is RecommendationsUiState.Loading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
    }
}