package com.android.battleoffavorites.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.battleoffavorites.R
import com.android.battleoffavorites.data.HomeData
import com.android.battleoffavorites.models.HomeModel

@Composable
fun TopBar(onCategoriesClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.categorys),
            contentDescription = "Kategoriler",
            modifier = Modifier
                .size(28.dp)
                .clickable { onCategoriesClick() }
        )
        Image(
            painter = painterResource(id = androidx.transition.R.drawable.abc_vector_test),
            contentDescription = "Uygulama Simgesi",
            modifier = Modifier.size(28.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profil",
            modifier = Modifier.size(28.dp)
        )
    }
}

@Composable
fun FloatingActionBar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color(0xFFBB4F30),
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.pencil),
            contentDescription = "Yeni Ekle",
            tint = Color.White,
            modifier = Modifier.size(26.dp)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginDialog(
    onDismiss: () -> Unit,
    onLogin: (String, String) -> Unit,
    onRegisterClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            // Card dışına tıklanırsa dialog kapanır.
            .clickable(onClick = onDismiss)
    ) {
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 18.dp)
                .clickable(enabled = true, onClick = { /* Tıklama Card içinde kalır */ }),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Giriş Yap",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("E-posta") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Şifre") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.outlinedTextFieldColors()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { onLogin(email, password) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Giriş Yap")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Hesabınız yoksa, Register olun",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Blue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onRegisterClick() },
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ResultPopup(message: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Tamam")
            }
        },
        title = { Text("Bilgi") },
        text = { Text(message) }
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onCategoriesClick = {  },
        onTestClick = { }
    )
}

@Composable
fun HomeScreen(
    onCategoriesClick: () -> Unit = {},
    onTestClick: (HomeModel) -> Unit = {}
) {

    var showLoginDialog by remember { mutableStateOf(false) }
    var loginResultMessage by remember { mutableStateOf<String?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF212121))
        ) {
            TopBar(onCategoriesClick = onCategoriesClick)
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(6.dp))
            val tests = HomeData.homeModels
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(tests) { test ->
                    TestCard(test = test, onClick = { onTestClick(test) })
                }
            }
        }
        FloatingActionBar(
            onClick = { showLoginDialog = true },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        )

        if (showLoginDialog) {
            LoginDialog(
                onDismiss = { showLoginDialog = false },
                onLogin = { email, password ->

                    if (email == "test@test.com" && password == "password") {
                        loginResultMessage = "Giriş başarılı"
                        showLoginDialog = false
                    } else {
                        loginResultMessage = "E-posta veya şifre hatalı"
                    }
                },
                onRegisterClick = {
                    showLoginDialog = false
                }
            )
        }

        if (loginResultMessage != null) {
            ResultPopup(message = loginResultMessage!!) {
                loginResultMessage = null
            }
        }
    }
}

@Composable
fun TestCard(test: HomeModel, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF33373B))
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = test.profileImageRes),
                    contentDescription = "Profil Resmi",
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = test.creatorName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = test.testName,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = test.testImageRes),
                contentDescription = "Test Resmi",
                modifier = Modifier
                    .fillMaxSize()
                    .height(120.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}