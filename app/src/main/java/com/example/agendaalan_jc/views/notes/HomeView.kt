package com.example.agendaalan_jc.views.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.agendaalan_jc.navigation.Routes
import com.example.agendaalan_jc.viewModels.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, notesViewModel: NotesViewModel) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Inicio") },
                actions = {
                    IconButton(
                        onClick = {
                            notesViewModel.logoOut()
                            navController.navigate(Routes.Login) {
                                popUpTo(Routes.Home) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Cerrar sesión"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {navController.navigate("AddNote")},
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
            )
            {
                Text(text = "Agregar Nota")
            }
        }
    }

}
