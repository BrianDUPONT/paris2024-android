package bts.sio.paris2024.viewsmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import bts.sio.paris2024.api.RetrofitInstance
import bts.sio.paris2024.model.Athlete
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

    /*init {
        getAthletes()
    }*/

     fun getAthletes() {
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

    fun getAthletesByPays(paysId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null  // Réinitialise l'erreur avant l'appel
            try {
                val response = RetrofitInstance.api.getAthletesByPaysId(paysId)
                _athletes.value = response

            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.localizedMessage ?: "Une erreur s'est produite"}"
            } finally {
                _isLoading.value = false
                println("Chargement des athlètes du pays selectionné terminé" + paysId )
            }
        }
    }

    fun addAthlete(athlete: Athlete) {
        viewModelScope.launch {
            _isLoading.value = true
            try {

                // Envoi à l'API (ici, un POST)
                val response = RetrofitInstance.api.addAthlete(athlete)
                if (response.isSuccessful) {
                    // Ajout réussi, on met à jour la liste des pays
                    getAthletes() // Recharge les pays pour inclure le nouveau
                } else {
                    _errorMessage.value = "Erreur lors de l'ajout du athlete : ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }



}