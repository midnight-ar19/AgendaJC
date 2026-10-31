package com.example.agendaalan_jc.views.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agendaalan_jc.R
import com.example.agendaalan_jc.navigation.Routes
import com.example.agendaalan_jc.viewModels.RegisterViewModel
import com.example.agendaalan_jc.viewModels.RegistrationValidation

@Composable
fun RegisterView(
    navController: NavController,
    registerViewModel: RegisterViewModel
) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var hasAttemptedSubmit by remember { mutableStateOf(false) }
    val validation = RegistrationValidation.validate(username, email, password)
    val isRegistering = registerViewModel.isRegistering

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "Registro de usuario",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Image(
            modifier = Modifier
                .height(130.dp)
                .width(130.dp)
                .padding(12.dp),
            painter = painterResource(id = R.drawable.registro),
            contentDescription = "Icono de registro"
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = {
                email = it
                registerViewModel.clearError()
            },
            label = { Text("Email") },
            singleLine = true,
            isError = hasAttemptedSubmit && validation.emailError != null,
            supportingText = if (hasAttemptedSubmit && validation.emailError != null) {
                { Text(validation.emailError) }
            } else null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = username,
            onValueChange = {
                username = it
                registerViewModel.clearError()
            },
            label = { Text("Username") },
            singleLine = true,
            isError = hasAttemptedSubmit && validation.usernameError != null,
            supportingText = if (hasAttemptedSubmit && validation.usernameError != null) {
                { Text(validation.usernameError) }
            } else null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {
                password = it
                registerViewModel.clearError()
            },
            label = { Text("Password") },
            singleLine = true,
            isError = hasAttemptedSubmit && validation.passwordError != null,
            supportingText = if (hasAttemptedSubmit && validation.passwordError != null) {
                { Text(validation.passwordError) }
            } else null,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        registerViewModel.errorMessage?.let { message ->
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                hasAttemptedSubmit = true
                if (validation.isValid) {
                    registerViewModel.createUser(username, email.trim(), password) {
                        navController.navigate(Routes.Home) {
                            popUpTo(Routes.Register) { inclusive = true }
                        }
                    }
                }
            },
            enabled = !isRegistering,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isRegistering) {
                CircularProgressIndicator(
                    modifier = Modifier.size(18.dp),
                    strokeWidth = 2.dp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Creando cuenta...")
            } else {
                Text("Registrarme")
            }
        }

        TextButton(
            onClick = { navController.popBackStack() },
            enabled = !isRegistering,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al login")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterViewPreview() {
    RegisterView(
        navController = rememberNavController(),
        registerViewModel = remember { RegisterViewModel() }
    )
}
