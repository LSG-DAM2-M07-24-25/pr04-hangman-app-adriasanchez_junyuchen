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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun GameScreen(word: String, navigateToBack: () -> Unit, navigateToFinal: (Boolean) -> Unit) {
    val new_word by remember { mutableStateOf(emptyWord(word)) }

    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "Game word:", fontSize = 25.sp)
        Text(text = new_word, fontSize = 25.sp)
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navigateToBack() }) {
            Text(text = "Back")
        }
        Spacer(modifier = Modifier.weight(1f))
        Row (horizontalArrangement = Arrangement.SpaceEvenly) {
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { navigateToFinal(false) }) {
                Text(text = "Lose")
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { navigateToFinal(true) }) {
                Text(text = "Win")
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

fun emptyWord(word: String): String {
    var new_word = ""
    for (i in word) {
        new_word += "_"
    }
    return new_word.toCharArray().joinToString(" ")
}