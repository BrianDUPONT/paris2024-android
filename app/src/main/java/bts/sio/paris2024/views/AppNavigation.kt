package bts.sio.paris2024.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import bts.sio.paris2024.views.actualites.ActualiteList
import bts.sio.paris2024.views.athletes.AthleteAdd
import bts.sio.paris2024.views.epreuves.EpreuveList
import bts.sio.paris2024.views.athletes.AthleteList
import bts.sio.paris2024.views.joueurs.JoueurList
import bts.sio.paris2024.views.pays.PaysAdd
import bts.sio.paris2024.views.pays.PaysList
import bts.sio.paris2024.views.sports.SportList


@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "actualites_list",
        modifier = modifier
    ) {
        composable("actualites_list") {
            //Text("Page actualites")
            ActualiteList()
        }
        composable("epreuves_list") {
            //Text("Page epreuves")
            EpreuveList()
        }
        composable("athletes_list") {
            //Text("Page athletes")
            AthleteList(navController = navController)
        }
        composable("sports_list") {
            //Text("Page sports")
            SportList()
        }
        composable("pays_list") {
            //Text("Page pays")
            PaysList(navController = navController)
        }

        composable("add_pays") {
            PaysAdd(navController = navController)
        }

        composable(
            "add_athlete/{paysId}",
            arguments = listOf(navArgument("paysId") { type = NavType.IntType })
        ) { backStackEntry ->
            val paysId = backStackEntry.arguments?.getInt("paysId") ?: -1
            if (paysId != -1) {
                AthleteAdd(paysId = paysId, navController = navController)
            } else {
                Text("Erreur : Identifiant de bâtiment manquant")
            }
        }

        composable("joueurs_list") {
            JoueurList()
        }

        composable("athletes_list?paysId={paysId}") { backStackEntry ->
            val paysId = backStackEntry.arguments?.getString("paysId")?.toIntOrNull()
            AthleteList(paysId = paysId, navController = navController)
        }
    }
}