package com.adriajunyu.hangman.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adriajunyu.hangman.ui.models.Difficulty

class GameViewModel(new_word: String? = null, difficulty: Difficulty? = Difficulty.EASY) : ViewModel() {
    val easy_words = arrayOf(
        "gato", "sol", "luna", "arbol", "casa", "mesa", "flor", "perro", "boca", "libro",
        "agua", "raton", "nieve", "leche", "viento", "plaza", "zapato", "piedra", "fruta", "piedra",
        "rojo", "azul", "silla", "tren", "pelota", "bajo", "cielo", "papa", "pollo", "vaca"
    )

    val medium_words = arrayOf(
        "ventana", "playa", "escuela", "cafe", "raton", "silla", "nube", "lluvia", "coche", "piedra",
        "manzana", "luz", "dinero", "alfombra", "espejo", "cuchara", "cerca", "camisa", "piedra", "rojo",
        "mariposa", "calle", "almohada", "peluche", "baile", "mueble", "solucion", "pintura", "naranja", "hoja"
    )

    val hard_words = arrayOf(
        "guitarra", "bicicleta", "computadora", "mariposa", "helicoptero", "helado", "banana", "murcielago", "futbol", "telescopio",
        "electromagnetismo", "microscopio", "bipolaridad", "inmortalidad", "multinacional", "evolucion", "acelerador", "transistor", "cibernetico", "abominacion",
        "universo", "espectro", "circulante", "pseudonimo", "conveniencia", "oxigeno", "dinamico", "simulador", "compuerta", "arquelogia"
    )


    private val _alphabet = MutableLiveData<List<Char>>(('A'..'Z').toList())
    val alphabet : LiveData<List<Char>> = _alphabet

    private val difficulty = difficulty

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

    private val maximumAttempts = 7

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

    fun getRandomWord(): String {
        return when (difficulty) {
            Difficulty.EASY -> easy_words.random()
            Difficulty.MEDIUM -> medium_words.random()
            Difficulty.HARD -> hard_words.random()
            null -> easy_words.random()
        }
    }

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