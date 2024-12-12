package com.adriajunyu.hangman.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adriajunyu.hangman.R
import com.adriajunyu.hangman.ui.viewmodels.GameViewModel

@Composable
fun GameScreen(
    navigateToBack: () -> Unit,
    navigateToFinal: (Boolean, Int) -> Unit,
    viewModel: GameViewModel
) {
    val hiddenWord by viewModel.hidenWord.observeAsState("")
    val win by viewModel.win.observeAsState(false)
    val lose by viewModel.lose.observeAsState(false)
    val attempts by viewModel.attempts.observeAsState(0)
    val navigated = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.5f
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Text(text = stringResource(id = R.string.game_word), fontSize = 25.sp)
            Text(text = hiddenWord, fontSize = 25.sp)
            Text(text = stringResource(id = R.string.attempts, attempts), fontSize = 25.sp)

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = getHangmanImage(attempts)),
                contentDescription = stringResource(id = R.string.hangman),
                modifier = Modifier.size(400.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = { navigateToBack() }) {
                Text(text = stringResource(id = R.string.back))
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Adaptive(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(viewModel.alphabet.value!!) { letter ->
                    LetterButton(letter = letter.toString(), enabled = true) {
                        viewModel.showLetter(letter)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }

    if ((win || lose || attempts >= 7) && !navigated.value) {
        navigated.value = true
        navigateToFinal(win, attempts)
    }
}

fun getHangmanImage(attempts: Int): Int {
    return when (attempts) {
        0 -> R.drawable.hangman_1
        1 -> R.drawable.hangman_2
        2 -> R.drawable.hangman_3
        3 -> R.drawable.hangman_4
        4 -> R.drawable.hangman_5
        5 -> R.drawable.hangman_6
        6 -> R.drawable.hangman_7
        else -> R.drawable.hangman_7
    }
}

@Composable
fun LetterButton(letter: String, enabled: Boolean, onClick: () -> Unit) {
    Button(onClick = onClick, enabled = enabled, modifier = Modifier.fillMaxWidth()) {
        Text(text = letter)
    }
}