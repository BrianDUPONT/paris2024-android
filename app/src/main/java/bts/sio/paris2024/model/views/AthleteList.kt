package bts.sio.paris2024.model.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.paris2024.model.viewsmodel.AthleteViewModel

@Composable
fun AthleteList(viewModel: AthleteViewModel = viewModel()) {
    // Observer les données de manière réactive
    val athletes by viewModel.athletes.collectAsState()

    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        items(athletes) { athlete ->
            AthleteCard(athlete = athlete)
        }
    }
}