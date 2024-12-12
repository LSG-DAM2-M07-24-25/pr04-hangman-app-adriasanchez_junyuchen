package com.adriajunyu.hangman.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adriajunyu.hangman.R
import com.adriajunyu.hangman.ui.models.Difficulty
import com.adriajunyu.hangman.ui.viewmodels.GameViewModel

@Composable
fun MenuScreen(navigateToGame: (String) -> Unit) {
    var selectedDifficulty by remember { mutableStateOf(Difficulty.EASY) }
    var expanded by remember { mutableStateOf(false) }
    Column (modifier = Modifier
        .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = stringResource(id = R.string.app_name), fontSize = 25.sp)
        Image(
            painter = painterResource(id = R.drawable.hangman_7),
            contentDescription = stringResource(id = R.string.app_name),
            modifier = Modifier.size(250.dp)
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.select_difficulty, selectedDifficulty.name),
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
                    .padding(16.dp)
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                Difficulty.entries.forEach { difficulty ->
                    DropdownMenuItem(
                        text = { Text(text = difficulty.name) },
                        onClick = {
                            selectedDifficulty = difficulty
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        // Al llamar a la funcion navigateToGame, se navega a la pantalla de juego,
        // con la palabra "hello" como parametro
        Button(onClick = { navigateToGame(GameViewModel().getRandomWord()) }) {
            Text(text = stringResource(id = R.string.start_game))
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}