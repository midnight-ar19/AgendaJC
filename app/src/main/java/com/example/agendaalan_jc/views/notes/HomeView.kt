package com.example.agendaalan_jc.views.notes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.agendaalan_jc.viewModels.NotesViewModel

@Composable
fun HomeView(navController: NavController, notesViewModel: NotesViewModel) {
    Text(text = "Bienvenido - INICIO")
}
