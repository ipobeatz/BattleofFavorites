package com.android.battleoffavorites.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.battleoffavorites.data.CategoryMockData
import com.android.battleoffavorites.data.HomeData
import com.android.battleoffavorites.models.HomeModel


@Composable
fun CategoryDetailScreen(categoryId: Int) {

    val category = CategoryMockData.categories.firstOrNull { it.id == categoryId }

    val testsForCategory = HomeData.homeModels.filter { it.categoryId == categoryId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF212121))
    ) {
        // Üstte başlık için header card (sadece kategori adı, fotoğraf yok)
        if (category != null) {
            category.bgColor?.let { CardDefaults.cardColors(containerColor = it) }?.let {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = it,
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = "Kategori: ${category.name}",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()   // Ekranın tüm genişliğini kaplar
                            .padding(10.dp),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center  // Metni ortalar
                    )
                }
            }
        } else {
            Text(
                text = "Kategori bulunamadı",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }
        // Alt kısımda kategoriye ait testleri alt alta listeleyen LazyColumn
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(testsForCategory) { test ->
                TestCard(test = test, onClick = { /* Test detayına gitme işlemi */ })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryDetailScreenPreview() {
    CategoryDetailScreen(categoryId = 1)
}