package com.adriajunyu.hangman.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object Menu

@Serializable
data class Game (val word: String)

@Serializable
data class Final (val win: Boolean)