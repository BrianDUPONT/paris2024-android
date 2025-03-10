package bts.sio.paris2024.model.viewsmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import bts.sio.paris2024.model.Epreuve
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import bts.sio.paris2024.api.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EpreuveViewModel : ViewModel() {

    // Liste mutable des epreuves
    private val _epreuves = MutableStateFlow<List<Epreuve>>(emptyList())
    val epreuves: StateFlow<List<Epreuve>> = _epreuves

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        getEpreuves()
    }

    private fun getEpreuves() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null  // Réinitialise l'erreur avant l'appel

            try {
                val response = RetrofitInstance.api.getEpreuves()
                _epreuves.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.localizedMessage ?: "Une erreur s'est produite"}"
            } finally {
                _isLoading.value = false
                println("Chargement terminé")
            }
        }
    }


}