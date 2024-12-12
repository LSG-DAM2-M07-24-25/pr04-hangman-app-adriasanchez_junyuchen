package com.adriajunyu.hangman.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adriajunyu.hangman.R
import com.adriajunyu.hangman.ui.viewmodels.GameViewModel
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier

@Composable
fun GameScreen(navigateToBack: () -> Unit, navigateToFinal: (Boolean, Int) -> Unit, viewModel: GameViewModel) {
    val hidenWord by viewModel.hidenWord.observeAsState("")
    val win by viewModel.win.observeAsState(false)
    val lose by viewModel.lose.observeAsState(false)
    val attempts by viewModel.attempts.observeAsState(0)
    val navigated = remember { mutableStateOf(false) }

    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = stringResource(id = R.string.game_word), fontSize = 25.sp)
        Text(text = hidenWord, fontSize = 25.sp)
        Text(text = stringResource(id = R.string.attempts, attempts), fontSize = 25.sp)
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navigateToBack() }) {
            Text(text = stringResource(id = R.string.back))
        }
        Spacer(modifier = Modifier.weight(1f))

        if ((win || lose) && !navigated.value) {
            navigated.value = true
            navigateToFinal(win, attempts)
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.alphabet) { letter ->
                LetterButton(letter = letter, enabled = true) {
                    viewModel.showLetter(letter)
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun LetterButton(letter: Char, enabled: Boolean, onClick: () -> Unit) {
    Button(onClick = onClick, enabled = enabled) {
        Text(text = letter.toString())
    }
}