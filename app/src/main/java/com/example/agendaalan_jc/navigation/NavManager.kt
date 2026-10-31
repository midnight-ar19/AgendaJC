package com.example.agendaalan_jc.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.agendaalan_jc.viewModels.LoginViewModel
import com.example.agendaalan_jc.viewModels.NotesViewModel
import com.example.agendaalan_jc.viewModels.RegisterViewModel
import com.example.agendaalan_jc.views.login.LoginView
import com.example.agendaalan_jc.views.login.checkSession
import com.example.agendaalan_jc.views.notes.AddNoteView
import com.example.agendaalan_jc.views.notes.HomeView
import com.example.agendaalan_jc.views.register.RegisterView

object Routes {
    const val CheckSession = "CheckSession"
    const val Login = "login"
    const val Register = "register"
    const val Home = "home"
    const val AddNote = "AddNote"
}

@Composable
fun NavManager(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    notesViewModel: NotesViewModel,
    contentPadding: PaddingValues = PaddingValues()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.CheckSession,
        modifier = Modifier.padding(contentPadding)
    ) {
        composable(Routes.CheckSession) {
            checkSession(navController)
        }
        composable(Routes.Login) {
            LoginView(
                navController = navController,
                loginViewModel = loginViewModel
            )
        }
        composable(Routes.Register) {
            RegisterView(
                navController = navController,
                registerViewModel = registerViewModel
            )
        }
        composable(Routes.Home) {
            HomeView(
                navController = navController,
                notesViewModel = notesViewModel
            )
        }

        composable(Routes.AddNote) {
            AddNoteView(
                navController = navController,
                notesViewModel = notesViewModel
            )
        }
    }
    
}
