package com.example.vocal.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocal.data.Graph
import com.example.vocal.data.models.Word
import com.example.vocal.data.services.MainService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WordViewModel(private val service : MainService = Graph.mainService) : ViewModel() {

    lateinit var allWords : Flow<List<Word>>
    var wordIdState by mutableStateOf(0)
    var wordTextState by mutableStateOf("")
    var wordMeaningState by mutableStateOf("")
    var wordDifficultyState by mutableStateOf(0)

    private val _searchedWords = MutableStateFlow<List<Word>>(emptyList())
    val searchedWords: StateFlow<List<Word>> = _searchedWords

    private val _randomWordList = MutableStateFlow<List<Word>>(emptyList())
    val randomWordList: StateFlow<List<Word>> = _randomWordList

    // getting all the words while creating the view model
    init {
        viewModelScope.launch {
            allWords = service.getAllWords()
        }
    }

    fun cleanStates() {
        wordIdState = 0
        wordTextState = ""
        wordMeaningState = ""
        wordDifficultyState = 0
    }

    fun addWord(word : Word) {
        viewModelScope.launch(Dispatchers.IO) {
            service.addWord(word)
        }
        cleanStates()
    }

    fun updateWord(word : Word) {
        viewModelScope.launch(Dispatchers.IO) {
            service.updateWord(word)
        }
        cleanStates()
    }

    fun deleteWord(word : Word) {
        viewModelScope.launch(Dispatchers.IO) {
            service.deleteWord(word)
        }
    }

    fun getWordsByDifficulty(difficulty : Int, num : Int) {
        viewModelScope.launch {
            service.getWordsByDifficulty(difficulty, num)
                .collect {wordList ->
                    _randomWordList.value = wordList
                }
        }
    }

    fun getWordsByPrefix(prefix: String) {
        viewModelScope.launch {
            service.getWordsByPrefix("%$prefix%")
                .collect { wordList ->
                    _searchedWords.value = wordList
                }
        }
    }

    fun getWordsByRandom(num : Int) {
        viewModelScope.launch {
            service.getWordsByRandom(num)
                .collect{ randomList ->
                    _randomWordList.value = randomList
                }
        }
    }
}