package bts.sio.paris2024.model.viewsmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bts.sio.paris2024.model.Epreuve
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EpreuveViewModel : ViewModel() {

    // Liste mutable des epreuves
    private val _epreuves = MutableStateFlow<List<Epreuve>>(emptyList())
    val epreuves: StateFlow<List<Epreuve>> = _epreuves

    init {
        // Simuler un chargement de données initiales
        getEpreuves()
    }

    // Fonction pour simuler le chargement de bâtiments
    private fun getEpreuves() {
        viewModelScope.launch {
            _epreuves.value = listOf(
                Epreuve(1, "100 mètres"),
                Epreuve(2, "Marathon"),
                Epreuve(3, "Saut en longueur")
            )
        }
    }
}