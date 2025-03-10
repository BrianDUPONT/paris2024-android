package bts.sio.paris2024.api

import bts.sio.paris2024.model.Actualite
import bts.sio.paris2024.model.Epreuve
import retrofit2.http.GET

interface ApiService {
    @GET("/actualites")
    suspend fun getActualites(): List<Actualite>

    @GET("/epreuves")
    suspend fun getEpreuves(): List<Epreuve>
}