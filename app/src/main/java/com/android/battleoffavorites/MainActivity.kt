// File: MainActivity.kt
package com.android.battleoffavorites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.battleoffavorites.data.HomeData
import com.android.battleoffavorites.screens.CategoriesScreen
import com.android.battleoffavorites.screens.CategoryDetailScreen
import com.android.battleoffavorites.screens.HomeScreen
import com.android.battleoffavorites.screens.TestScreen
import com.android.battleoffavorites.ui.theme.BattleOfFavoritesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BattleOfFavoritesTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavigationGraph()
                }
            }
        }
    }
}

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                onCategoriesClick = {
                    navController.navigate(Screen.Categories.route)
                },
                onTestClick = { test ->
                    navController.navigate("test/${test.id}")
                }
            )
        }
        composable(Screen.Categories.route) {
            CategoriesScreen(navController)
        }
        composable("categoryDetail/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull() ?: 0
            CategoryDetailScreen(categoryId = categoryId, navController = navController)
        }
        composable(
            route = "test/{testId}",
            arguments = listOf(navArgument("testId") { type = NavType.IntType })
        ) { backStackEntry ->
            val testId = backStackEntry.arguments?.getInt("testId") ?: -1
            val selectedTest = HomeData.homeModels.find { it.id == testId }
            if (selectedTest != null) {
                TestScreen(homeModel = selectedTest)
            }
        }
    }
}