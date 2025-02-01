package com.android.battleoffavorites.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.battleoffavorites.R
import com.android.battleoffavorites.data.HomeData

@Composable
fun TopBar(onCategoriesClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Sol tarafta: Kategoriler simgesi; tıklanabilir ve onCategoriesClick tetikleniyor.
        Image(
            painter = painterResource(id = R.drawable.categorys),
            contentDescription = "Kategoriler",
            modifier = Modifier
                .size(28.dp)
                .clickable { onCategoriesClick() }
        )
        // Ortada: Uygulamanın simgesi
        Image(
            painter = painterResource(id = androidx.transition.R.drawable.abc_vector_test),
            contentDescription = "Uygulama Simgesi",
            modifier = Modifier.size(28.dp)
        )
        // Sağ tarafta: Profil simgesi
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profil",
            modifier = Modifier.size(28.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onCategoriesClick = { /* Örneğin: navController.navigate("categories") */ },
        onTestClick = { }
    )
}

@Composable
fun HomeScreen(
    onCategoriesClick: () -> Unit = {},
    onTestClick: (TestModel) -> Unit = {}
) {
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
        val tests = HomeData.testModels
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
}

@Composable
fun TestCard(test: TestModel, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF282A2E))
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