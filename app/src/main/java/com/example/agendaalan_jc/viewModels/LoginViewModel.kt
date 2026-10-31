package com.example.agendaalan_jc.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


class LoginViewModel() : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    fun login(email: String, password: String, onSucces: () -> Unit){
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        task ->  if (task.isSuccessful) onSucces()
                    }
                    .addOnFailureListener {
                        Log.d("Error", "No se pudo iniciar sesion")
                    }
            } catch (e: Exception){
                Log.d("Error", "${e.message}")
            }
        }
    }
}