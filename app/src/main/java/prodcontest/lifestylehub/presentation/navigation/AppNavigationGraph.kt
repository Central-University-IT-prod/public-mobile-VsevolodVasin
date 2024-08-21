package prodcontest.lifestylehub.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import prodcontest.lifestylehub.presentation.auth.AuthScreen
import prodcontest.lifestylehub.presentation.home.HomeScreen
import prodcontest.lifestylehub.presentation.home.recommendation.details.RecommendationDetailsScreen
import prodcontest.lifestylehub.presentation.leisure.LeisureScreen
import prodcontest.lifestylehub.presentation.leisure.details.LeisureDetailsScreen

@Composable
fun AppNavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Destinations.Home.route) {
        composable(Destinations.Home.route) {
            HomeScreen(navController)
        }
        composable(Destinations.Leisure.route) {
            LeisureScreen(navController)
        }
        composable(Destinations.Account.route) {
            AuthScreen(navController)
        }
        composable("${Destinations.PlaceDetails.route}?recommendationId={recommendationId}",
            arguments = listOf(navArgument("recommendationId") { defaultValue = "" })) { backStackEntry ->
            RecommendationDetailsScreen(backStackEntry.arguments?.getString("recommendationId")!!)
        }

        composable("${Destinations.LeisureDetails.route}?leisureId={leisureId}",
            arguments = listOf(navArgument("leisureId") { defaultValue=0})) { backStackEntry ->
            LeisureDetailsScreen(navController, backStackEntry.arguments?.getInt("leisureId")!!)
        }
     }
}