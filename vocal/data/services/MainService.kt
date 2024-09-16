package com.example.vocal.data.services

import com.example.vocal.data.models.Word
import com.example.vocal.data.repositories.WordRepository
import kotlinx.coroutines.flow.Flow

class MainService(private val repository : WordRepository) {

    suspend fun addWord(word : Word) {
        repository.addWord(word)
    }

    fun getAllWords() : Flow<List<Word>> = repository.getAllWords()

    suspend fun updateWord(word : Word) {
        repository.updateWord(word)
    }

    suspend fun deleteWord(word : Word) {
        repository.deleteWord(word)
    }

    fun getWordsByDifficulty(difficulty : Int, num : Int) : Flow<List<Word>> = repository.getWordsByDifficulty(difficulty, num)

    fun getWordsByPrefix(prefix : String) : Flow<List<Word>> = repository.getWordsByPrefix(prefix)

    fun getWordsByRandom(num : Int) : Flow<List<Word>> = repository.getWordsByRandom(num)

}