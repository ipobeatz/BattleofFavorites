package com.android.battleoffavorites.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.battleoffavorites.R
import com.android.battleoffavorites.data.CarDataProvider
import com.android.battleoffavorites.models.HomeModel
import com.android.battleoffavorites.models.TestModel
import kotlinx.coroutines.delay


@Preview(showBackground = true)
@Composable
fun TestScreenPreview() {
    val sampleTest = TestModel(
        id = 1,
        imageRes = R.drawable.profile,
        name = "En İyi Araba Testi",
    )

}

@Composable
fun TestScreen(homeModel: HomeModel) {
    val homeModel = homeModel.testName


    val imageResources: List<TestModel> = CarDataProvider.testModels


    val initialCarImages = remember { imageResources.shuffled() }
    var currentRound by remember { mutableStateOf(initialCarImages) }
    var currentMatchIndex by remember { mutableStateOf(0) }
    var winners by remember { mutableStateOf(emptyList<TestModel>()) }
    var champion by remember { mutableStateOf<TestModel?>(null) }
    var roundNumber by remember { mutableStateOf(1) }

    fun advanceRound() {
        if (winners.size == 1) {
            champion = winners.first()
        } else {
            currentRound = winners.shuffled()
            winners = emptyList()
            currentMatchIndex = 0
            roundNumber++
        }
    }

    fun selectWinner(selected: TestModel) {
        winners = winners + selected
        currentMatchIndex += 2

        if (currentRound.size % 2 == 1 && currentMatchIndex == currentRound.size - 1) {
            winners = winners + currentRound.last()
            currentMatchIndex++
        }
        if (currentMatchIndex >= currentRound.size) {
            advanceRound()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2D2D2D))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = homeModel, style = MaterialTheme.typography.headlineMedium,color = Color.White, fontSize = 22.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Round: $roundNumber", style = MaterialTheme.typography.headlineSmall,color = Color.White)
        Text(
            text = "Match: ${currentMatchIndex / 2 + 1} / ${currentRound.size / 2}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 4.dp),
            color = Color.White
        )
        Spacer(modifier = Modifier.height(24.dp))

        if (champion != null) {
            Text(text = "Şampiyon!", style = MaterialTheme.typography.headlineLarge,color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                shape = MaterialTheme.shapes.medium,
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = champion!!.imageRes),
                        contentDescription = "Şampiyon Fotoğraf",
                        modifier = Modifier.size(240.dp)
                    )
                    Text(
                        text = champion!!.name,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        } else {
            // Turnuva devam ediyor
            if (currentMatchIndex < currentRound.size - 1) {
                val car1 = currentRound[currentMatchIndex]
                val car2 = currentRound[currentMatchIndex + 1]
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // İlk resim ve ismi
                    Card(
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        modifier = Modifier.clickable { selectWinner(car1) }
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = car1.imageRes),
                                contentDescription = car1.name,
                                modifier = Modifier.size(240.dp)
                            )
                            Text(
                                text = car1.name,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "VS", style = MaterialTheme.typography.headlineSmall,color = Color.White)
                    Spacer(modifier = Modifier.height(8.dp))
                    // İkinci resim ve ismi
                    Card(
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        modifier = Modifier.clickable { selectWinner(car2) }
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = car2.imageRes),
                                contentDescription = car2.name,
                                modifier = Modifier.size(240.dp)
                            )
                            Text(
                                text = car2.name,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                }
            } else if (currentRound.size % 2 == 1 && currentMatchIndex == currentRound.size - 1) {
                // Eğer round'da tek eleman kaldıysa
                val lastCar = currentRound.last()
                Text(
                    "Sadece 1 eleman kaldı, otomatik geçiş...",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    shape = MaterialTheme.shapes.medium,
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = lastCar.imageRes),
                            contentDescription = lastCar.name,
                            modifier = Modifier.size(150.dp)
                        )
                        Text(
                            text = lastCar.name,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
                LaunchedEffect(lastCar) {
                    delay(1000)
                    winners = winners + lastCar
                    currentMatchIndex++
                    if (currentMatchIndex >= currentRound.size) {
                        advanceRound()
                    }
                }
            }
        }
    }
}