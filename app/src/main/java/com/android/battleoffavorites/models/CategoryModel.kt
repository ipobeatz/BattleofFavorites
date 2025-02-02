package com.android.battleoffavorites.models

import androidx.compose.ui.graphics.Color

data class CategoryModel(
    val id: Int,
    val name: String,
    val iconRes: Int,
    val bgColor: Color? = null
)