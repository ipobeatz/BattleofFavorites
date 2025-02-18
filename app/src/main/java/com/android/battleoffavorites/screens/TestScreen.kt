// File: TestScreen.kt
package com.android.battleoffavorites.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.battleoffavorites.R
import com.android.battleoffavorites.data.CarDataProvider
import com.android.battleoffavorites.models.HomeModel
import com.android.battleoffavorites.models.TestModel
import kotlinx.coroutines.delay

@Composable
fun TestScreen(homeModel: HomeModel) {
    val testTitle = homeModel.testName

    // Select the test data based on HomeModel.id:
    val imageResources: List<TestModel> = when (homeModel.id) {
        1 -> CarDataProvider.testModels1
        2 -> CarDataProvider.testModels2
        else -> CarDataProvider.testModels1  // Default case
    }

    // Randomize the order of test models.
    val initialTestModels = remember { imageResources.shuffled() }
    var currentRound by remember { mutableStateOf(initialTestModels) }
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
        Text(
            text = testTitle,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Round: $roundNumber",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White
        )
        Text(
            text = "Match: ${currentMatchIndex / 2 + 1} / ${currentRound.size / 2}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 4.dp),
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))

        if (champion != null) {
            Text(
                text = "Champion!",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                shape = MaterialTheme.shapes.medium,
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = champion!!.imageRes),
                        contentDescription = "Champion Image",
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
            if (currentMatchIndex < currentRound.size - 1) {
                val test1 = currentRound[currentMatchIndex]
                val test2 = currentRound[currentMatchIndex + 1]
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        modifier = Modifier.clickable { selectWinner(test1) }
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = test1.imageRes),
                                contentDescription = test1.name,
                                modifier = Modifier.size(240.dp)
                            )
                            Text(
                                text = test1.name,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "VS",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        modifier = Modifier.clickable { selectWinner(test2) }
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = test2.imageRes),
                                contentDescription = test2.name,
                                modifier = Modifier.size(240.dp)
                            )
                            Text(
                                text = test2.name,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                }
            } else if (currentRound.size % 2 == 1 && currentMatchIndex == currentRound.size - 1) {
                val lastTest = currentRound.last()
                Text(
                    text = "Only one test left, auto advancing...",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    shape = MaterialTheme.shapes.medium,
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = lastTest.imageRes),
                            contentDescription = lastTest.name,
                            modifier = Modifier.size(150.dp)
                        )
                        Text(
                            text = lastTest.name,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
                LaunchedEffect(lastTest) {
                    delay(1000)
                    winners = winners + lastTest
                    currentMatchIndex++
                    if (currentMatchIndex >= currentRound.size) {
                        advanceRound()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestScreenPreview() {
    val sampleHomeModel = HomeModel(
        id = 1,
        creatorName = "Alice",
        profileImageRes = R.drawable.abc,
        testName = "En İyi Araba Testi",
        testImageRes = R.drawable.adonis,
        categoryId = 1
    )
    TestScreen(homeModel = sampleHomeModel)
}