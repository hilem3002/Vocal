package com.example.vocal.data.repositories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.vocal.data.models.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordRepository {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addWord(word : Word)

    @Query("Select * from `word-table`")
    fun getAllWords() : Flow<List<Word>>

    @Update
    fun updateWord(word : Word)

    @Delete
    fun deleteWord(word : Word)

    @Query("Select * from `word-table` where difficulty=:difficulty order by random() limit :num")
    fun getWordsByDifficulty(difficulty : Int, num : Int) : Flow<List<Word>>

    @Query("Select * from `word-table` where text like :prefix or meaning like :prefix")
    fun getWordsByPrefix(prefix : String) : Flow<List<Word>>

    @Query("Select * from `word-table` order by random() limit :num")
    fun getWordsByRandom(num : Int) : Flow<List<Word>>

}