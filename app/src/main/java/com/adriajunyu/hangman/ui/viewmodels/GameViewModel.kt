package com.adriajunyu.hangman.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel(new_word: String? = null) : ViewModel() {
    private val words = listOf("a", "ab", "abc", "abcd", "abcde")
    private val _word = MutableLiveData<String>()
    val word : LiveData<String> = _word

    private val _hidenWord = MutableLiveData<String>()
    val hidenWord : LiveData<String> = _hidenWord

    private val noLetter = '_'

    init {
        if (new_word != null) {
            setWord(new_word)
        }
    }

    fun setWord(word: String) {
        _word.value = word
        _hidenWord.value = emptyWord(word)
    }

    fun emptyWord(word: String): String {
        var new_word = ""
        for (i in word) {
            new_word += noLetter
        }
        return new_word.toCharArray().joinToString(" ")
    }

    fun getRandomWord(): String = words.random().lowercase() ?: "android"

    fun showLetter(letter: Char) {
        val word = _word.value ?: ""
        val wordArray = word.toCharArray()
        val hidenWord = _hidenWord.value ?: ""
        val hidenWordArray = hidenWord.replace(" ", "").toCharArray()
        var new_hidenWord = CharArray(hidenWordArray.size)
        for (i in wordArray.indices) {
            if (wordArray[i].lowercase() == letter.lowercase()) {
                new_hidenWord[i] = letter
            } else {
                new_hidenWord[i] = hidenWordArray[i]
            }
        }
        _hidenWord.value = new_hidenWord.joinToString(" ")
    }
}