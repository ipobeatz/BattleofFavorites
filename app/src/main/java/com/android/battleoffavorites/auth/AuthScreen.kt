package com.android.battleoffavorites.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AuthScreen(viewModel: AuthViewModel = viewModel(), onSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isRegister by remember { mutableStateOf(false) }

    val user by viewModel.user.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(user) {
        if (user != null) {
            onSuccess()  // Başarılı giriş sonrası yönlendirme
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (isRegister) "Kayıt Ol" else "Giriş Yap",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-posta") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Şifre") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                println("Giriş Butonuna Basıldı!")  // Konsola yazdır

                if (isRegister) {
                    viewModel.register(email, password)
                } else {
                    viewModel.login(email, password)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (isRegister) "Kayıt Ol" else "Giriş Yap")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = { isRegister = !isRegister }) {
            Text(
                text = if (isRegister) "Zaten hesabın var mı? Giriş Yap" else "Hesabın yok mu? Kayıt Ol"
            )
        }

        if (errorMessage != null) {
            Text(text = "Hata: $errorMessage", color = MaterialTheme.colorScheme.error)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen(onSuccess = {})
}
