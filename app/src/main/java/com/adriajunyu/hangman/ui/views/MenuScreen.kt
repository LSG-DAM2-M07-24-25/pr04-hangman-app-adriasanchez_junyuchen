package com.adriajunyu.hangman.ui.views

import androidx.compose.foundation.layout.Column
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
fun MenuScreen(navigateToGame: (String) -> Unit) {
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = stringResource(id = R.string.app_name), fontSize = 25.sp)
        Spacer(modifier = Modifier.weight(1f))
        // Al llamar a la funcion navigateToGame, se navega a la pantalla de juego,
        // con la palabra "hello" como parametro
        Button(onClick = { navigateToGame("hello") }) {
            Text(text = stringResource(id = R.string.start_game))
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}