package com.example.agendaalan_jc.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agendaalan_jc.models.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val auth : FirebaseAuth = Firebase.auth

    var isRegistering by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun clearError() {
        errorMessage = null
    }

    fun createUser(username : String, email : String , password : String, onSucces : () -> Unit)
    {
        if (isRegistering) return

        isRegistering = true
        errorMessage = null
        viewModelScope.launch {
            try{
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        isRegistering = false
                        if(task.isSuccessful){
                            saveUser(username)
                            onSucces()
                        }else{
                            errorMessage = task.exception?.localizedMessage
                                ?.takeIf { it.isNotBlank() }
                                ?: "No se pudo crear la cuenta. Inténtalo de nuevo."
                        }
                    }
            }catch(e: Exception){
                isRegistering = false
                errorMessage = e.localizedMessage
                    ?.takeIf { it.isNotBlank() }
                    ?: "No se pudo crear la cuenta. Inténtalo de nuevo."
            }
        }
    }

    fun saveUser(username: String){
        val id = auth.currentUser?.uid
        val email = auth.currentUser?.email

        viewModelScope.launch ( context = Dispatchers.IO ){
            val user = UserModel(id.toString() , email.toString() , username )

            FirebaseFirestore.getInstance().collection("Users")
                .add(user)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Error", "Se guardo la informacion del usuario")
                    }
                }
                .addOnFailureListener { e -> Log.d("Error", "${e.message}") }
        }
    }

}

data class RegistrationValidationResult(
    val usernameError: String?,
    val emailError: String?,
    val passwordError: String?
) {
    val isValid: Boolean
        get() = usernameError == null && emailError == null && passwordError == null
}

object RegistrationValidation {
    private val emailPattern = Regex("^[A-Z0-9+_.-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$", RegexOption.IGNORE_CASE)

    fun validate(username: String, email: String, password: String): RegistrationValidationResult {
        val usernameError = if (username.isBlank()) "El nombre de usuario es obligatorio" else null
        val normalizedEmail = email.trim()
        val emailError = when {
            normalizedEmail.isBlank() -> "El email es obligatorio"
            !emailPattern.matches(normalizedEmail) -> "Introduce un email válido"
            else -> null
        }
        val passwordError = when {
            password.isBlank() -> "La contraseña es obligatoria"
            password.length < 8 -> "La contraseña debe tener al menos 8 caracteres"
            else -> null
        }

        return RegistrationValidationResult(usernameError, emailError, passwordError)
    }
}
