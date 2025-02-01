package com.android.battleoffavorites

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Categories : Screen("categories")
    object Test : Screen("test/{testId}") // Test ID ile geçiş
}