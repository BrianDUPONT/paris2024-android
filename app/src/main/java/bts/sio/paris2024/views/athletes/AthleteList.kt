package bts.sio.paris2024.views.athletes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import bts.sio.paris2024.views.athletes.AthleteCard
import bts.sio.paris2024.viewsmodel.AthleteViewModel
import bts.sio.paris2024.viewsmodel.PaysViewModel

@Composable
fun AthleteList(
    paysId: Int? = null,
    athleteViewModel: AthleteViewModel = viewModel(),
    paysViewModel: PaysViewModel = viewModel(),
    navController: NavController
) {
    val athletes by athleteViewModel.athletes.collectAsState()
    val isLoading by athleteViewModel.isLoading.collectAsState()
    val errorMessage by athleteViewModel.errorMessage.collectAsState()
    val pays by paysViewModel.pays.collectAsState()

    LaunchedEffect(paysId) {
        if (paysId == null) {
            athleteViewModel.getAthletes()
        } else {
            athleteViewModel.getAthletesByPays(paysId)
            paysViewModel.getPays(paysId)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            errorMessage != null -> {
                Text(
                    text = errorMessage ?: "Erreur inconnue",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    color = MaterialTheme.colorScheme.error
                )
            }
            else -> {
                LazyColumn {
                    if (pays != null) {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally // Centrer le contenu horizontalement
                            ) {
                                Text(
                                    text = "Informations sur le pays",
                                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Pays : ${pays?.nom ?: "Non défini"}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        item {
                            Button(
                                onClick = {
                                    navController.navigate("add_athlete/${paysId}")
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(Icons.Default.Add, contentDescription = "Ajouter")
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Ajouter un athlete")
                                }
                            }
                        }
                    }

                    if (athletes.isNotEmpty()) {
                        item {
                            Text(
                                text = "Liste des athletes",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 1.dp)
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        items(athletes) { athlete ->
                            AthleteCard(athlete = athlete, navController = navController)
                        }
                    } else {
                        item {
                            Text(
                                text = "Pas d'athlete pour ce bâtiment",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 1.dp)
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}