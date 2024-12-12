package com.adriajunyu.hangman.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel(new_word: String? = null) : ViewModel() {
    private val words = listOf("a", "ab", "abc", "abcd", "abcde")
    val alphabet = listOf(
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',  'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    )

    private val _word = MutableLiveData<String>()
    val word : LiveData<String> = _word

    private val _hidenWord = MutableLiveData<String>()
    val hidenWord : LiveData<String> = _hidenWord

    private val _win = MutableLiveData<Boolean>()
    val win : LiveData<Boolean> = _win

    private val _lose = MutableLiveData<Boolean>()
    val lose : LiveData<Boolean> = _lose

    private val _attempts = MutableLiveData<Int>()
    val attempts : LiveData<Int> = _attempts

    private val maximumAttempts = 5

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
        var correct = false

        val word = _word.value ?: ""
        val wordArray = word.toCharArray()
        val hidenWord = _hidenWord.value ?: ""
        val hidenWordArray = hidenWord.replace(" ", "").toCharArray()
        val new_hidenWord = CharArray(hidenWordArray.size)

        for (i in wordArray.indices) {
            if (wordArray[i].lowercase() == letter.lowercase()) {
                new_hidenWord[i] = letter
                correct = true
            } else {
                new_hidenWord[i] = hidenWordArray[i]
            }
        }

        _hidenWord.value = new_hidenWord.joinToString(" ")

        if (_word.value?.lowercase() == _hidenWord.value?.lowercase()?.replace(" ", "")) {
            _win.value = true
            return
        }

        if (!correct) {
            _attempts.value = (_attempts.value ?: 0) + 1
            if (_attempts.value == maximumAttempts) {
                _lose.value = true
            }
        }
    }
}