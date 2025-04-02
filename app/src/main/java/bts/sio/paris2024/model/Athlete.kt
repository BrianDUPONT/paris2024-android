package bts.sio.paris2024.model

import java.time.LocalDate

data class Athlete(
    val id: Int,
    val nom: String,
    val prenom: String,
    val dateNaiss: String,
    val pays: Pays,
    val sport: Sport
)
