package com.adriajunyu.hangman.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.adriajunyu.hangman.R
import com.adriajunyu.hangman.ui.viewmodels.GameViewModel

@Composable
fun GameScreen(new_word: String, navigateToBack: () -> Unit, navigateToFinal: (Boolean, Int) -> Unit, viewModel: GameViewModel) {
    viewModel.setWord(new_word)

    val hidenWord by viewModel.hidenWord.observeAsState(viewModel.emptyWord(new_word))

    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = stringResource(id = R.string.game_word), fontSize = 25.sp)
        Text(text = hidenWord, fontSize = 25.sp)
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navigateToBack() }) {
            Text(text = stringResource(id = R.string.back))
        }
        Spacer(modifier = Modifier.weight(1f))
        Row (horizontalArrangement = Arrangement.SpaceEvenly) {
            Spacer(modifier = Modifier.weight(1f))
            // Al llamar a la funcion navigateToFinal, se navega a la pantalla final
            // con el parametro win = false y attempts = 5
            Button(onClick = { navigateToFinal(false, 5) }) {
                Text(text = stringResource(id = R.string.lose))
            }
            Spacer(modifier = Modifier.weight(1f))
            // Al llamar a la funcion navigateToFinal, se navega a la pantalla final
            // con el parametro win = true y attempts = 2
            Button(onClick = { navigateToFinal(true, 2) }) {
                Text(text = stringResource(id = R.string.win))
            }
            Spacer(modifier = Modifier.weight(1f))
            Button( onClick = { viewModel.showLetter('a') } ) {
                Text(text = "Mostrar texto")
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}