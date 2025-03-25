package bts.sio.paris2024.views.pays

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import bts.sio.paris2024.viewsmodel.PaysViewModel

@Composable
fun PaysList(
    viewModel: PaysViewModel = viewModel(),
    navController: NavController,
    onAddPaysClick: () -> Unit
) {
    // Observer les données de manière réactive
    val pays by viewModel.pays.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    when {
        isLoading -> {
            CircularProgressIndicator()
        }
        errorMessage != null -> {
            Text(text = errorMessage!!, color = Color.Red)
        }
        else -> {
            Column {
                // Ajouter un bouton pour ajouter un pays
                Button(
                    onClick = onAddPaysClick,
                    modifier = Modifier
                        .widthIn(min = 150.dp, max = 300.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                ) {
                    Text("Ajouter un pays")
                }

                // Afficher la liste des pays
                LazyColumn {
                    items(pays) { pays ->
                        PaysCard(pays = pays, navController = navController)
                    }
                }
            }
        }
    }
}