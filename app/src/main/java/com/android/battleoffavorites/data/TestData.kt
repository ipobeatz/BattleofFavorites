// File: CarDataProvider.kt
package com.android.battleoffavorites.data

import com.android.battleoffavorites.R
import com.android.battleoffavorites.models.TestModel

object CarDataProvider {
    val testModels1: List<TestModel> = listOf(
        TestModel(1, R.drawable.abc, "ABC"),
        TestModel(2, R.drawable.acapulco, "Acapulco"),
        TestModel(3, R.drawable.adonis, "Adonis"),
        TestModel(4, R.drawable.alabamaslammer, "Alabama Slammer"),
        TestModel(5, R.drawable.alexander, "Alexander")
    )
    
    val testModels2: List<TestModel> = listOf(
        TestModel(1, R.drawable.baklava, "Baklava"),
        TestModel(2, R.drawable.kunefe, "Künefe"),
        TestModel(3, R.drawable.kazandibi, "Kazandibi"),
        TestModel(4, R.drawable.sutlec, "Sütlaç"),
        TestModel(5, R.drawable.sekerpare, "Şekerpare"),
        TestModel(6, R.drawable.asure, "Aşure"),
        TestModel(7, R.drawable.revani, "Revani"),
        TestModel(8, R.drawable.lokma, "Lokma"),
        TestModel(9, R.drawable.tulumba, "Tulumba Tatlısı"),
        TestModel(10, R.drawable.trilece, "Trileçe")
    )
}