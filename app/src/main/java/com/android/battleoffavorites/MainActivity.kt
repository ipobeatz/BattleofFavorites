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
import com.android.battleoffavorites.screens.HomeScreen
import com.android.battleoffavorites.screens.TestModel
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
                    navController.navigate("test/${test.id}") // ID'yi yolla
                }
            )
        }
        composable(Screen.Categories.route) {
            CategoriesScreen()
        }
        composable(
            route = Screen.Test.route,
            arguments = listOf(navArgument("testId") { type = NavType.IntType }) // ID parametresi
        ) { backStackEntry ->
            val testId = backStackEntry.arguments?.getInt("testId") ?: -1
            val selectedTest = HomeData.testModels.find { it.id == testId }

            if (selectedTest != null) {
                TestScreen(testModel = selectedTest)
            }
        }
    }
}