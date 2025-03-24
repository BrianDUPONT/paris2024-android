package bts.sio.paris2024.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import bts.sio.paris2024.model.views.actualites.ActualiteList
import bts.sio.paris2024.model.views.epreuves.EpreuveList
import bts.sio.paris2024.model.views.athletes.AthleteList
import bts.sio.paris2024.model.views.joueurs.JoueurList
import bts.sio.paris2024.model.views.pays.PaysList
import bts.sio.paris2024.model.views.sports.SportList

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
            AthleteList()
        }
        composable("sports_list") {
            //Text("Page sports")
            SportList()
        }
        composable("pays_list") {
            //Text("Page pays")
            PaysList()
        }
        composable("joueurs_list") {
            JoueurList()
        }
    }
}