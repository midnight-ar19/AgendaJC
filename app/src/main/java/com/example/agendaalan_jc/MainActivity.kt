package com.example.agendaalan_jc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.agendaalan_jc.navigation.NavManager
import com.example.agendaalan_jc.viewModels.LoginViewModel
import com.example.agendaalan_jc.viewModels.NotesViewModel
import com.example.agendaalan_jc.viewModels.RegisterViewModel

class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private val registerViewModel: RegisterViewModel by viewModels()
    private val notesViewModel: NotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            Scaffold(modifier = Modifier.fillMaxSize()) { contentPadding ->
                NavManager(
                    navController = navController,
                    loginViewModel = loginViewModel,
                    registerViewModel = registerViewModel,
                    notesViewModel = notesViewModel,
                    contentPadding = contentPadding
                )
            }
        }
    }
}
