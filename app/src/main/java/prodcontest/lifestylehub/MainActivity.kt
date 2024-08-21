package prodcontest.lifestylehub

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.yandex.mapkit.MapKitFactory
import org.koin.java.KoinJavaComponent.inject
import prodcontest.lifestylehub.presentation.home.weather.WeatherViewModel
import prodcontest.lifestylehub.presentation.navigation.AppNavigationGraph
import prodcontest.lifestylehub.presentation.navigation.BottomNavigationBar

class MainActivity : ComponentActivity() {

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapKitFactory.initialize(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ){
            val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
            val requestPermissionLauncher = registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { permissionsMap ->
               var allGranted = true
               for (entry in permissionsMap.entries.iterator()) {
                    if (!entry.value) {
                        allGranted = false
                    }
               }
               if (allGranted) {
                   val weatherViewModel : WeatherViewModel by inject(WeatherViewModel::class.java)
                   weatherViewModel.getWeather()
               }
            }
            requestPermissionLauncher.launch(permissions)
        }

        setContent {
            val navController = rememberNavController()

            AppTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    }
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                      AppNavigationGraph(navController = navController)
                  }
                }
            }
        }
    }


}



