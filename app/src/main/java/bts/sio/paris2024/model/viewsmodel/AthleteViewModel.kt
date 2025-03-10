package bts.sio.paris2024.model.viewsmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import bts.sio.paris2024.model.Athlete
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import bts.sio.paris2024.api.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AthleteViewModel : ViewModel() {

    // Liste mutable des athletes
    private val _athletes = MutableStateFlow<List<Athlete>>(emptyList())
    val athletes: StateFlow<List<Athlete>> = _athletes

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        getAthletes()
    }

    private fun getAthletes() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null  // Réinitialise l'erreur avant l'appel

            try {
                val response = RetrofitInstance.api.getAthletes()
                _athletes.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.localizedMessage ?: "Une erreur s'est produite"}"
            } finally {
                _isLoading.value = false
                println("Chargement terminé")
            }
        }
    }


}