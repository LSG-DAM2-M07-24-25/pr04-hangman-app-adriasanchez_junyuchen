package com.adriajunyu.hangman.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun MenuScreen(navigateToGame: (String) -> Unit) {
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "Hangman", fontSize = 25.sp)
        Spacer(modifier = Modifier.weight(1f))
        // Al llamar a la funcion navigateToGame, se navega a la pantalla de juego,
        // con la palabra "hello" como parametro
        Button(onClick = { navigateToGame("hello") }) {
            Text(text = "Start Game")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}