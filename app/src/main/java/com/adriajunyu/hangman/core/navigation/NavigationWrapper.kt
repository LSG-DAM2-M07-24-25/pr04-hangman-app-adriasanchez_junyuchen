package com.adriajunyu.hangman.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.adriajunyu.hangman.ui.viewmodels.GameViewModel
import com.adriajunyu.hangman.ui.views.FinalScreen
import com.adriajunyu.hangman.ui.views.GameScreen
import com.adriajunyu.hangman.ui.views.MenuScreen
import com.adriajunyu.hangman.ui.views.SplashScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Splash) {
        // Navegacion a la pantalla de inicio
        composable<Splash> {
            SplashScreen {
                navController.popBackStack()
                navController.navigate(Menu)
            }
        }

        // Navegacion a la pantalla de menu
        composable<Menu> {
            MenuScreen {
                word -> navController.navigate(Game(word = word))
            }
        }

        // Navegacion a la pantalla de juego
        composable<Game> { backStackEntry ->
            val game = backStackEntry.toRoute<Game>()
            GameScreen (
                navigateToBack = {
                    navController.navigate(Menu){
                        popUpTo(Menu) { inclusive = true }
                    }
                },
                navigateToFinal = { win: Boolean, attempts: Int ->
                    navController.navigate(Final(win = win, attempts = attempts)) {
                        popUpTo<Menu> { inclusive = false }
                    }
                },
                viewModel = GameViewModel(game.word)
            )
        }

        // Navegacion a la pantalla final
        composable<Final> { backStackEntry ->
            val final = backStackEntry.toRoute<Final>()
            FinalScreen (win = final.win, attempts = final.attempts,
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