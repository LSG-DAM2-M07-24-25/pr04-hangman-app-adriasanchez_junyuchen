package com.adriajunyu.hangman.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adriajunyu.hangman.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigateToMenu: () -> Unit) {
    LaunchedEffect(key1 = true) {
        delay(1000)
        navigateToMenu()
    }

    Splash()
}

@Composable
fun Splash() {
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = stringResource(id = R.string.app_name),
            modifier = Modifier.size(200.dp)
        )
        Text(text = stringResource(id = R.string.app_name), fontSize = 25.sp)
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen({})
}

