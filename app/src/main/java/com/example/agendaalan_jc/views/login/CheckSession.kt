package com.example.agendaalan_jc.views.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.agendaalan_jc.navigation.Routes
import com.google.firebase.auth.FirebaseAuth

@Composable
fun checkSession(navController: NavController) {
    LaunchedEffect(Unit) {
        if (!FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
            navController.navigate(Routes.Home) {
                popUpTo(Routes.CheckSession) { inclusive = true }
            }
        } else {
            navController.navigate(Routes.Login) {
                popUpTo(Routes.CheckSession) { inclusive = true }
            }
        }
    }
}