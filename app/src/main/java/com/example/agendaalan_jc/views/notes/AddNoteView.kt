package com.example.agendaalan_jc.views.notes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.agendaalan_jc.viewModels.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun AddNoteView(navController: NavController, notesViewModel: NotesViewModel){
    var title by remember { mutableStateOf((""))}
    var descripcion by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                {Text(text = "Agregar Nota")},
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "")
                    }
                }
            )
        }

    ) {paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally)
        {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp),
                value = title,
                onValueChange = {
                    title = it
                },
                label = { Text("Tittle") },
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp),
                value = descripcion,
                onValueChange = {
                    descripcion = it
                },
                label = { Text("Descripcion") }
            )

            Button(onClick = {},
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
                )
            {
                Text(text = "Agregar Nota")
            }
        }
    }

}