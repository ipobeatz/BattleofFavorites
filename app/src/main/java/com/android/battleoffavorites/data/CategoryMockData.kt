// File: CategoryMockData.kt
package com.android.battleoffavorites.data

import androidx.compose.ui.graphics.Color
import com.android.battleoffavorites.R
import com.android.battleoffavorites.models.CategoryModel

object CategoryMockData {
    val categories = listOf(
        CategoryModel(
            id = 1,
            name = "Eğlence",
            iconRes = R.drawable.abc,
            bgColor = Color(0xFF791E87)
        ),
        CategoryModel(
            id = 2,
            name = "Spor",
            iconRes = R.drawable.acapulco,
            bgColor = Color(0xFF3E8E42)
        ),
        CategoryModel(
            id = 3,
            name = "Oyun",
            iconRes = R.drawable.adonis,
            bgColor = Color(0xFF1562AF)
        ),
        CategoryModel(
            id = 4,
            name = "Müzik",
            iconRes = R.drawable.abc,
            bgColor = Color(0xFFC97A02)
        ),

        CategoryModel(
            id = 5,
            name = "Spor",
            iconRes = R.drawable.acapulco,
            bgColor = Color(0xFFB8194E)
        ),
        CategoryModel(
            id = 6,
            name = "Oyun",
            iconRes = R.drawable.adonis,
            bgColor = Color(0xFF5E4437)
        ),
        CategoryModel(
            id = 7,
            name = "Müzik",
            iconRes = R.drawable.abc,
            bgColor = Color(0xFF007167)
        ),
        CategoryModel(
            id = 8,
            name = "Spor",
            iconRes = R.drawable.acapulco,
            bgColor = Color(0xFF51781B)
        ),
        CategoryModel(
            id = 9,
            name = "Oyun",
            iconRes = R.drawable.adonis,
            bgColor = Color(0xFFB12828)
        )
    )
}