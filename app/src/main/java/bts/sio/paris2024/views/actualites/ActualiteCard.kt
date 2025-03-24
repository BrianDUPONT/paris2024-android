package bts.sio.paris2024.views.actualites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import bts.sio.paris2024.model.Actualite
import java.time.format.DateTimeFormatter


@Composable
fun ActualiteCard(actualite: Actualite) {
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
            // Ligne avec le titre à gauche et la date à droite
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = actualite.titre,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f) // La colonne du titre prend tout l'espace restant
                )
                // Formatage de la date
                val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
                val formattedDate = actualite.date.format(dateFormatter)
                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp) // Un peu d'espace entre le titre et la date
                )
            }

            // Ligne avec le contenu
            Text(
                text = actualite.contenu,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}