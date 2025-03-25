package bts.sio.paris2024.views.joueurs

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.paris2024.viewsmodel.JoueurViewModel

@Composable
fun JoueurList(viewModel: JoueurViewModel = viewModel()) {
    // Observer les données de manière réactive
    val joueurs by viewModel.joueurs.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    when {
        isLoading -> CircularProgressIndicator()
        errorMessage != null -> Text(text = errorMessage!!, color = Color.Red)
        else -> LazyColumn {
            items(joueurs) { joueur ->
                JoueurCard(joueur = joueur)
            }
        }
    }
}