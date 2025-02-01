package com.android.battleoffavorites.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.battleoffavorites.R
import kotlinx.coroutines.delay



// Tournament için kullanılan görsel model
data class CarImage(val id: Int, val imageRes: Int)

@Preview(showBackground = true)
@Composable
fun TestScreenPreview() {
    // Örnek model nesnesi; gerçek uygulamada ilgili verilerden alınabilir.
    val sampleTest = TestModel(
        id = 1,
        creatorName = "Creator",
        profileImageRes = R.drawable.profile,
        testName = "En İyi Araba Testi",
        testImageRes = R.drawable.abc
    )
    TestScreen(testModel = sampleTest)
}

@Composable
fun TestScreen(testModel: TestModel) {
    // Modelden gelen test adı ekranın üstünde gösterilecek.
    val testName = testModel.testName

    // Örnek resim kaynakları listesi (turnuva için)
    val imageResources: List<Int> = listOf(
        R.drawable.abc,
        R.drawable.acapulco,
        R.drawable.adonis,
        R.drawable.alabamaslammer,
        R.drawable.alexander,
        R.drawable.alexandersister,
        R.drawable.alexanderbrother,
        R.drawable.alienbrainhemorrage,
        R.drawable.amarettosour,
        R.drawable.amf,
        R.drawable.angelface,
        R.drawable.applecart,
        R.drawable.appletini,
        R.drawable.abc,
        R.drawable.acapulco,
        R.drawable.adonis
    )

    // Başlangıçta resimleri rastgele sırala ve CarImage nesnelerini oluştur.
    val initialCarImages = remember {
        imageResources.shuffled().mapIndexed { index, resId ->
            CarImage(id = index, imageRes = resId)
        }
    }

    var currentRound by remember { mutableStateOf(initialCarImages) }
    var currentMatchIndex by remember { mutableStateOf(0) }
    var winners by remember { mutableStateOf(emptyList<CarImage>()) }
    var champion by remember { mutableStateOf<CarImage?>(null) }
    var roundNumber by remember { mutableStateOf(1) }

    // Yeni round başlatmak için (kazananlar karıştırılarak yeni eşleşmeler oluşturuluyor)
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

    // Seçilen fotoğrafı kazananlara ekleyip sonraki eşleşmeye geçer
    fun selectWinner(selected: CarImage) {
        winners = winners + selected
        currentMatchIndex += 2

        // Eğer round tek sayıda eleman içeriyorsa, son eleman otomatik ekleniyor.
        if (currentRound.size % 2 == 1 && currentMatchIndex == currentRound.size - 1) {
            winners = winners + currentRound.last()
            currentMatchIndex++
        }
        if (currentMatchIndex >= currentRound.size) {
            advanceRound()
        }
    }

    if (champion != null) {
        // Şampiyon ekranı
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Şampiyon!", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                shape = MaterialTheme.shapes.medium,
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = champion!!.imageRes),
                    contentDescription = "Şampiyon Fotoğraf",
                    modifier = Modifier.size(200.dp)
                )
            }
        }
    } else {
        // Round devam ederken gösterilecek ekran
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Üst kısımda test adı, round ve match bilgileri
            Text(text = testName, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Round: $roundNumber", style = MaterialTheme.typography.headlineSmall)
            Text(
                text = "Match: ${currentMatchIndex / 2 + 1} / ${currentRound.size / 2}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 4.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            // Eşleşme ekranı: Dikey dizilimde fotoğraf, VS, fotoğraf
            if (currentMatchIndex < currentRound.size - 1) {
                val car1 = currentRound[currentMatchIndex]
                val car2 = currentRound[currentMatchIndex + 1]
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // İlk fotoğraf
                    Card(
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        modifier = Modifier.clickable { selectWinner(car1) }
                    ) {
                        Image(
                            painter = painterResource(id = car1.imageRes),
                            contentDescription = "Fotoğraf 1",
                            modifier = Modifier.size(150.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "VS", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(8.dp))
                    // İkinci fotoğraf
                    Card(
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        modifier = Modifier.clickable { selectWinner(car2) }
                    ) {
                        Image(
                            painter = painterResource(id = car2.imageRes),
                            contentDescription = "Fotoğraf 2",
                            modifier = Modifier.size(150.dp)
                        )
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
                    Image(
                        painter = painterResource(id = lastCar.imageRes),
                        contentDescription = "Tek Kalan Fotoğraf",
                        modifier = Modifier.size(150.dp)
                    )
                }
                LaunchedEffect(lastCar) {
                    delay(1000)
                    winners = winners + lastCar
                    currentMatchIndex++
                    if (currentMatchIndex >= currentRound.size) {
                        advanceRound()
                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Yükleniyor veya bekleniyor...", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}