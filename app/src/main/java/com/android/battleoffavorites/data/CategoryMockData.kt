package com.android.battleoffavorites.data

import androidx.compose.ui.graphics.Color
import com.android.battleoffavorites.R
import com.android.battleoffavorites.screens.CategoryModel

object CategoryMockData {
    val categories = listOf(
        CategoryModel(
            id = 1,
            name = "Müzik",
            iconRes = R.drawable.abc,
            bgColor = Color(0xFF791E87) // Orta koyu mor
        ),
        CategoryModel(
            id = 2,
            name = "Spor",
            iconRes = R.drawable.acapulco,
            bgColor = Color(0xFF3E8E42) // Orta koyu yeşil
        ),
        CategoryModel(
            id = 3,
            name = "Oyun",
            iconRes = R.drawable.adonis,
            bgColor = Color(0xFF1562AF) // Orta koyu mavi
        ),
        CategoryModel(
            id = 4,
            name = "Müzik",
            iconRes = R.drawable.abc,
            bgColor = Color(0xFFC97A02) // Orta koyu turuncu
        ),
        CategoryModel(
            id = 5,
            name = "Spor",
            iconRes = R.drawable.acapulco,
            bgColor = Color(0xFFB8194E) // Orta koyu pembe
        ),
        CategoryModel(
            id = 6,
            name = "Oyun",
            iconRes = R.drawable.adonis,
            bgColor = Color(0xFF5E4437) // Orta koyu kahverengi
        ),
        CategoryModel(
            id = 7,
            name = "Müzik",
            iconRes = R.drawable.abc,
            bgColor = Color(0xFF007167) // Orta koyu turkuaz
        ),
        CategoryModel(
            id = 8,
            name = "Spor",
            iconRes = R.drawable.acapulco,
            bgColor = Color(0xFF51781B) // Orta koyu nane yeşili
        ),
        CategoryModel(
            id = 9,
            name = "Oyun",
            iconRes = R.drawable.adonis,
            bgColor = Color(0xFFB12828) // Orta koyu kırmızı
        ),
        CategoryModel(
            id = 10,
            name = "Müzik",
            iconRes = R.drawable.abc,
            bgColor = Color(0xFFD2A026) // Orta koyu sarı
        ),
        CategoryModel(
            id = 11,
            name = "Spor",
            iconRes = R.drawable.acapulco,
            bgColor = Color(0xFF502D92) // Orta koyu leylak
        ),
        CategoryModel(
            id = 12,
            name = "Oyun",
            iconRes = R.drawable.adonis,
            bgColor = Color(0xFF4B606C) // Orta koyu gri-mavi
        )
    )
}
