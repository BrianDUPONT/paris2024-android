package bts.sio.paris2024.model.viewsmodel

import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bts.sio.paris2024.model.Athlete
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class AthleteViewModel : ViewModel() {

    // Liste mutable des athletes
    private val _athletes = MutableStateFlow<List<Athlete>>(emptyList())
    val athletes: StateFlow<List<Athlete>> = _athletes

    init {
        // Simuler un chargement de données initiales
        getAthletes()
    }

    // Fonction pour simuler le chargement de bâtiments
    private fun getAthletes() {
        viewModelScope.launch {
            _athletes.value = listOf(
                Athlete(1, "Dupont", "Jean", LocalDate.of(1990, 5, 15)),
                Athlete(2, "Tanaka", "Yuki", LocalDate.of(1990, 5, 15)),
                Athlete(2, "Smith", "John", LocalDate.of(1990, 5, 15)),
            )
        }
    }
}