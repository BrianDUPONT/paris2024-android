package bts.sio.paris2024.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "actualites_list",
        modifier = modifier
    ) {
        composable("actualites_list") {
            Text("Page actualites")
        }
        composable("epreuves_list") {
            Text("Page epreuves")
        }
        composable("athletes_list") {
            Text("Page athletes")
        }
        composable("sports_list") {
            Text("Page sports")
        }
        composable("pays_list") {
            Text("Page pays")
        }
    }
}