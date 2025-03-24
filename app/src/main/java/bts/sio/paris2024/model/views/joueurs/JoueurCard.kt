package bts.sio.paris2024.model.views.joueurs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bts.sio.paris2024.model.Joueur
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun JoueurCard(joueur: Joueur) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            val dateFormatter = DateTimeFormatter.ofPattern( "dd MMM yyyy")
            val formattedDate = joueur.dateNaiss.format(dateFormatter)

            Text(
                text = "${joueur.nom} ${joueur.prenom}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Date de naissance : $formattedDate",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Niveau : ${joueur.niveau.libelle}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Sport favori : ${joueur.sport.nom}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}