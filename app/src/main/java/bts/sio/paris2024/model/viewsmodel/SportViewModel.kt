package bts.sio.paris2024.model.viewsmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bts.sio.paris2024.model.Sport
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SportViewModel : ViewModel() {

    // Liste mutable des sports
    private val _sports = MutableStateFlow<List<Sport>>(emptyList())
    val sports: StateFlow<List<Sport>> = _sports

    init {
        // Simuler un chargement de données initiales
        getSports()
    }

    // Fonction pour simuler le chargement de bâtiments
    private fun getSports() {
        viewModelScope.launch {
            _sports.value = listOf(
                Sport(1, "Football", "Sport collectif joué entre deux équipes de 11 joueurs."),
                Sport(2, "Basket-ball", "Sport collectif joué entre deux équipes de 5 joueurs."),
                Sport(3, "Natation", "Sport individuel consistant à nager dans une piscine.")
            )
        }
    }
}