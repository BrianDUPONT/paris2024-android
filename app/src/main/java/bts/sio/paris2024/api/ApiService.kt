package bts.sio.paris2024.api

import bts.sio.paris2024.model.Actualite
import bts.sio.paris2024.model.Athlete
import bts.sio.paris2024.model.Epreuve
import bts.sio.paris2024.model.Pays
import bts.sio.paris2024.model.Sport
import retrofit2.http.GET

interface ApiService {
    @GET("/actualites")
    suspend fun getActualites(): List<Actualite>

    @GET("/epreuves")
    suspend fun getEpreuves(): List<Epreuve>

    @GET("/sports")
    suspend fun getSports(): List<Sport>

    @GET("/pays")
    suspend fun getPays(): List<Pays>

    @GET("/athletes")
    suspend fun getAthletes(): List<Athlete>
}