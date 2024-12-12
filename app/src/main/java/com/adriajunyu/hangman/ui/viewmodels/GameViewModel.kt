package com.adriajunyu.hangman.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel() : ViewModel() {
    private val words = listOf("a", "ab", "abc", "abcd", "abcde")
    private val _word = MutableLiveData<String>()
    private val _hidenWord = MutableLiveData<String>()
    val word : LiveData<String> = _word
    val hidenWord : LiveData<String> = _hidenWord

    fun setWord(word: String) {
        _word.value = word
    }

    fun emptyWord(word: String): String {
        var new_word = ""
        for (i in word) {
            new_word += "_"
        }
        return new_word.toCharArray().joinToString(" ")
    }

    fun getRandomWord(): String = words.random().lowercase() ?: "android"

    fun showLetter(letter: Char) {
        _hidenWord.value = _word.value
    }
}