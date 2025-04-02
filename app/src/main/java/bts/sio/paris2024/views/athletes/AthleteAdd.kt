package bts.sio.paris2024.views.athletes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import bts.sio.paris2024.model.Athlete
import bts.sio.paris2024.model.Pays
import bts.sio.paris2024.model.Sport
import bts.sio.paris2024.viewsmodel.AthleteViewModel
import bts.sio.paris2024.viewsmodel.PaysViewModel
import bts.sio.paris2024.viewsmodel.SportViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AthleteAdd(paysId: Int, navController: NavController) {
    val athleteViewModel: AthleteViewModel = viewModel()
    val paysViewModel: PaysViewModel = viewModel()
    val sportViewModel: SportViewModel = viewModel()

    var nom by remember { mutableStateOf("") }
    var prenom by remember { mutableStateOf("") }
    var dateNaiss by remember { mutableStateOf("") }
    var selectedSportId by remember { mutableStateOf<Int?>(null) }

    // États pour le sélecteur de date
    var showDatePicker by remember { mutableStateOf(false) }

    // Observer les états du ViewModel pour le pays et les sports
    val pays = paysViewModel.pays.collectAsState().value
    val sports = sportViewModel.sports.collectAsState().value ?: emptyList()

    // Chargement des données au début
    LaunchedEffect(paysId) {
        paysViewModel.getPays(paysId)
        sportViewModel.getSports()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Ajouter un athlète",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Afficher le pays sélectionné
        pays?.let {
            Text(
                text = "Pays: ${it.nom}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        OutlinedTextField(
            value = nom,
            onValueChange = { nom = it },
            label = { Text("Nom") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = prenom,
            onValueChange = { prenom = it },
            label = { Text("Prénom") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Champ de date avec sélecteur
        OutlinedTextField(
            value = dateNaiss,
            onValueChange = { /* Ne rien faire ici, la date est mise à jour par le sélecteur */ },
            readOnly = true,
            label = { Text("Date de naissance") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Sélectionner une date"
                    )
                }
            }
        )

        // Date Picker Dialog
        if (showDatePicker) {
            val datePickerState = rememberDatePickerState()

            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val localDate = Instant.ofEpochMilli(millis)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                            // Format YYYY-DD-MM comme requis
                            dateNaiss = localDate.format(DateTimeFormatter.ofPattern("yyyy-dd-MM"))
                        }
                        showDatePicker = false
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) {
                        Text("Annuler")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sélection du sport avec ExposedDropdownMenuBox
        SportDropdownField(
            sports = sports,
            selectedSportId = selectedSportId,
            onSportSelected = { selectedSportId = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (nom.isNotBlank() && prenom.isNotBlank() && dateNaiss.isNotBlank() && selectedSportId != null && pays != null) {
                    // Récupérer le sport sélectionné
                    val sport = sports.find { it.id == selectedSportId }

                    // S'assurer que le sport existe
                    if (sport != null) {
                        val athlete = Athlete(
                            id = 0,
                            nom = nom,
                            prenom = prenom,
                            dateNaiss = dateNaiss,
                            sport = sport,
                            pays = pays
                        )

                        athleteViewModel.addAthlete(athlete)
                        navController.popBackStack()
                    }
                }
            },
            modifier = Modifier.align(Alignment.End),
            enabled = nom.isNotBlank() && prenom.isNotBlank() &&
                    dateNaiss.isNotBlank() && selectedSportId != null && pays != null
        ) {
            Text("Ajouter l'athlète")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportDropdownField(
    sports: List<Sport>,
    selectedSportId: Int?,
    onSportSelected: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedSportId?.let { id ->
                sports.find { it.id == id }?.nom ?: ""
            } ?: "",
            onValueChange = { },
            readOnly = true,
            label = { Text("Sport") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            sports.forEach { sport ->
                DropdownMenuItem(
                    text = { Text(sport.nom) },
                    onClick = {
                        onSportSelected(sport.id)
                        expanded = false
                    }
                )
            }
        }
    }
}