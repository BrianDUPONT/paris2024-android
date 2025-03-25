package bts.sio.paris2024.api

import bts.sio.paris2024.model.Actualite
import bts.sio.paris2024.model.Athlete
import bts.sio.paris2024.model.Epreuve
import bts.sio.paris2024.model.Joueur
import bts.sio.paris2024.model.Pays
import bts.sio.paris2024.model.Sport
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("/actualites")
    suspend fun getActualites(): List<Actualite>

    @GET("/epreuves")
    suspend fun getEpreuves(): List<Epreuve>

    @GET("/sports")
    suspend fun getSports(): List<Sport>

    @GET("/pays")
    suspend fun getPays(): List<Pays>

    @GET("/pays/{id}")
    suspend fun getPaysById(@Path("id") id: Int): Response<Pays>

    @GET("/athletes")
    suspend fun getAthletes(): List<Athlete>

    @GET("/athletes/pays/{paysId}")
    suspend fun getAthletesByPaysId(@Path("paysId") paysId: Int): List<Athlete>

    @GET("/joueurs")
    suspend fun getJoueurs(): List<Joueur>

    @POST("/pays")
    suspend fun addPays(@Body pays: Pays): Response<Pays>
}