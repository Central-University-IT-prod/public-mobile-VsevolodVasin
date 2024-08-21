package prodcontest.lifestylehub.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
)  {
    data object Home : Destinations(
        route = "home",
        title = "Главная",
        icon = Icons.Outlined.Home
    )

    data object Leisure : Destinations(
        route = "leisure",
        title = "Мой досуг",
        icon = Icons.Outlined.DateRange
    )

    data object Account : Destinations(
        route = "account",
        title = "Профиль",
        icon = Icons.Outlined.Person
    )

    data object PlaceDetails : Destinations(
        route = "place",
        title = "Место",
        icon = Icons.Outlined.Place
    )

    data object LeisureDetails : Destinations(
        route = "leisure",
        title = "Досуг",
        icon = Icons.Outlined.CheckCircle
    )

}