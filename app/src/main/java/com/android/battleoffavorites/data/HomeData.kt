// File: HomeData.kt
package com.android.battleoffavorites.data

import com.android.battleoffavorites.R
import com.android.battleoffavorites.models.HomeModel

object HomeData {
    val homeModels = listOf(
        HomeModel(1, "Alice", R.drawable.abc, "1 numara", R.drawable.adonis, categoryId = 1),  // Örneğin, kategori 1 ("Eğlence" ya da ilgili kategori adı)
        HomeModel(2, "Murat Ç", R.drawable.murat, "En iyi Tatlılar", R.drawable.hafiz, categoryId = 2),
        HomeModel(3, "Charlie", R.drawable.abc, "En iyi video oyun müzikleri En iyi video oyun müzikleri En iyi video oyun müzikleri", R.drawable.adonis, categoryId = 3),
        HomeModel(4, "Diana", R.drawable.abc, "Test 4", R.drawable.adonis, categoryId = 4),
        HomeModel(5, "Eva", R.drawable.abc, "Test 5", R.drawable.adonis, categoryId = 5),
        HomeModel(6, "Frank", R.drawable.abc, "Test 6", R.drawable.adonis, categoryId = 6),
        HomeModel(7, "Charlie", R.drawable.abc, "Test 3", R.drawable.adonis, categoryId = 1),  // Aynı kategori 1'den bir başka test
        HomeModel(8, "Diana", R.drawable.abc, "Test 4", R.drawable.adonis, categoryId = 2),
        HomeModel(9, "Eva", R.drawable.abc, "Test 5", R.drawable.adonis, categoryId = 3),
        HomeModel(10, "Frank", R.drawable.abc, "Test 6", R.drawable.adonis, categoryId = 4)
    )
}