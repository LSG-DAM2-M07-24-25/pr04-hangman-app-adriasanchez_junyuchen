package com.adriajunyu.hangman.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.adriajunyu.hangman.R

@Composable
fun FinalScreen(win: Boolean, attempts: Int, navigateToGame: (String) -> Unit, navigateToMenu: () -> Unit) {
    val message = if (win) stringResource(R.string.you_win) else stringResource(R.string.you_lose)
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = message, fontSize = 25.sp)
        Text(text = "Attempts: $attempts", fontSize = 25.sp)
        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.weight(1f))
        Row (horizontalArrangement = Arrangement.SpaceEvenly) {
            Spacer(modifier = Modifier.weight(1f))
            // Al llamar a la funcion navigateToMenu, se navega a la pantalla de menu
            Button(onClick = { navigateToMenu() }) {
                Text(text = stringResource(id = R.string.exit))
            }
            Spacer(modifier = Modifier.weight(1f))
            // Al llamar a la funcion navigateToGame, se navega a la pantalla de juego,
            // con la palabra "bye" como parametro
            Button(onClick = { navigateToGame("bye") }) {
                Text(text = stringResource(id = R.string.restart))
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}