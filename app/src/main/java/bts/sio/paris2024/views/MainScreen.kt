package bts.sio.paris2024.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController



@Composable
fun MainScreen() {

    val navController = rememberNavController()
    Scaffold(
        topBar = {
            AppHeader() // Affiche l'en-tête
        },
        bottomBar = {
            AppBottomBar(navController = navController) // Affiche la barre de navigation en bas
        }
    ) { innerPadding ->
        // Contenu principal de l'écran
        AppNavigation(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}