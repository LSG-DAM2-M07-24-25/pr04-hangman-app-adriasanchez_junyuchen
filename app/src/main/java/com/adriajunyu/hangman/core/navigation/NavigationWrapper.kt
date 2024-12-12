package com.adriajunyu.hangman.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adriajunyu.hangman.ui.models.Difficulty
import com.adriajunyu.hangman.ui.viewmodels.GameViewModel
import com.adriajunyu.hangman.ui.views.FinalScreen
import com.adriajunyu.hangman.ui.views.GameScreen
import com.adriajunyu.hangman.ui.views.MenuScreen
import com.adriajunyu.hangman.ui.views.SplashScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        // Navigate to Splash Screen
        composable("splash") {
            SplashScreen {
                navController.popBackStack()
                navController.navigate("menu")
            }
        }

        // Navigate to Menu Screen
        composable("menu") {
            MenuScreen { word, difficulty ->
                navController.navigate("game/$word/${difficulty.name}")
            }
        }

        // Navigate to Game Screen
        composable("game/{word}/{difficulty}") { backStackEntry ->
            val word = backStackEntry.arguments?.getString("word") ?: ""
            val difficulty = Difficulty.valueOf(
                backStackEntry.arguments?.getString("difficulty") ?: Difficulty.EASY.name
            )
            GameScreen(
                navigateToBack = {
                    navController.navigate("menu") {
                        popUpTo("menu") { inclusive = true }
                    }
                },
                navigateToFinal = { win: Boolean, attempts: Int ->
                    navController.navigate("final/$win/$attempts") {
                        popUpTo("menu") { inclusive = false }
                    }
                },
                viewModel = GameViewModel(word),
                difficulty = difficulty
            )
        }

        // Navigate to Final Screen
        composable("final/{win}/{attempts}") { backStackEntry ->
            val win = backStackEntry.arguments?.getString("win")?.toBoolean() ?: false
            val attempts = backStackEntry.arguments?.getString("attempts")?.toInt() ?: 0
            FinalScreen(
                win = win,
                attempts = attempts,
                navigateToMenu = {
                    navController.navigate("menu") {
                        popUpTo("menu") { inclusive = true }
                    }
                },
                navigateToGame = { word ->
                    navController.navigate("game/$word/${Difficulty.EASY.name}") {
                        popUpTo("menu") { inclusive = false }
                    }
                }
            )
        }
    }
}