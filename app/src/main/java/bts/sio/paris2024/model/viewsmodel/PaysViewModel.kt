package bts.sio.paris2024.model.viewsmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bts.sio.paris2024.model.Pays
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PaysViewModel : ViewModel() {

    // Liste mutable des pays
    private val _pays = MutableStateFlow<List<Pays>>(emptyList())
    val pays: StateFlow<List<Pays>> = _pays

    init {
        // Simuler un chargement de données initiales
        getPays()
    }

    // Fonction pour simuler le chargement de bâtiments
    private fun getPays() {
        viewModelScope.launch {
            _pays.value = listOf(
                Pays(1, "France"),
                Pays(2, "Japon"),
                Pays(3, "États-Unis")
            )
        }
    }
}