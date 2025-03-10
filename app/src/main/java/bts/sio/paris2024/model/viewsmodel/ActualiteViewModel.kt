package bts.sio.paris2024.model.viewsmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bts.sio.paris2024.model.Actualite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ActualiteViewModel : ViewModel() {

    // Liste mutable des actualites
    private val _actualites = MutableStateFlow<List<Actualite>>(emptyList())
    val actualites: StateFlow<List<Actualite>> = _actualites

    init {
        // Simuler un chargement de données initiales
        getActualites()
    }

    // Fonction pour simuler le chargement de bâtiments
    private fun getActualites() {
        viewModelScope.launch {
            _actualites.value = listOf(
                Actualite(1, "Records battus aux JO 2020", "Les records ont été battus dans diverses épreuves comme le 100m."),
                Actualite(2, "Préparations pour les JO 2024", "Les équipes se préparent pour les jeux Olympiques à Paris."),
                Actualite(3, "Nouvelle technologie en natation", "Des nouvelles technologies ont été utilisées pour améliorer la performance en natation.")
            )
        }
    }
}