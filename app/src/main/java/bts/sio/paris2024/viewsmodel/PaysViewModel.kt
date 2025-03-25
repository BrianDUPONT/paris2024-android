package bts.sio.paris2024.viewsmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import bts.sio.paris2024.model.Pays
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import bts.sio.paris2024.api.RetrofitInstance
import bts.sio.paris2024.model.Athlete
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PaysViewModel : ViewModel() {

    // Liste mutable des pays
    private val _pays = MutableStateFlow<List<Pays>>(emptyList())
    val pays: StateFlow<List<Pays>> = _pays

    private val _paysById = MutableStateFlow<Pays?>(null)
    val idPays: StateFlow<Pays?> = _paysById

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage


    init {
        getPays()
    }

    fun getPays() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null  // Réinitialise l'erreur avant l'appel

            try {
                val response = RetrofitInstance.api.getPays()
                _pays.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.localizedMessage ?: "Une erreur s'est produite"}"
            } finally {
                _isLoading.value = false
                println("Chargement du pays terminé")
            }
        }
    }

    fun getPaysById(paysId : Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null  // Réinitialise l'erreur avant l'appel

            try {
                val response = RetrofitInstance.api.getPaysById(paysId)
                _paysById.value = response.body()
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.localizedMessage ?: "Une erreur s'est produite"}"
            } finally {
                _isLoading.value = false
                println("Chargement du pays terminé")
            }
        }
    }

    fun addPays(pays: Pays) {
        viewModelScope.launch {
            _isLoading.value = true
            try {

                // Envoi à l'API (ici, un POST)
                val response = RetrofitInstance.api.addPays(pays)
                if (response.isSuccessful) {
                    // Ajout réussi, on met à jour la liste des bâtiments
                    getPays() // Recharge les bâtiments pour inclure le nouveau
                } else {
                    _errorMessage.value = "Erreur lors de l'ajout du pays : ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}