package com.adriajunyu.hangman.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.adriajunyu.hangman.ui.views.FinalScreen
import com.adriajunyu.hangman.ui.views.GameScreen
import com.adriajunyu.hangman.ui.views.MenuScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Menu) {
        composable<Menu> {
            MenuScreen {
                word -> navController.navigate(Game(word = word))
            }
        }

        composable<Game> { backStackEntry ->
            val game = backStackEntry.toRoute<Game>()
            GameScreen (word = game.word,
                navigateToBack = {
                    navController.navigate(Menu){
                        popUpTo(Menu) { inclusive = true }
                    }
                },
                navigateToFinal = { win ->
                    navController.navigate(Final(win = win)) {
                        popUpTo<Menu> { inclusive = false }
                    }
                }
            )
        }

        composable<Final> { backStackEntry ->
            val final = backStackEntry.toRoute<Final>()
            FinalScreen (win = final.win,
                navigateToMenu = {
                    navController.navigate(Menu){
                        popUpTo(Menu) { inclusive = true }
                    }
                },
                navigateToGame = { word ->
                    navController.navigate(Game(word = word)) {
                        popUpTo(Menu) { inclusive = false }
                    }
                }
            )
        }
    }
}