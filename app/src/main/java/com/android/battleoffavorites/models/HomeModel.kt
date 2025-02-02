// File: HomeModel.kt
package com.android.battleoffavorites.models

data class HomeModel(
    val id: Int,
    val creatorName: String,
    val profileImageRes: Int,
    val testName: String,
    val testImageRes: Int,
    val categoryId: Int
)