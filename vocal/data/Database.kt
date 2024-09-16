package com.example.vocal.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.vocal.data.models.Word
import com.example.vocal.data.repositories.WordRepository

@Database(
    entities = [Word::class],
    version = 1,
    exportSchema = false
)

abstract class Database : RoomDatabase() {
    abstract fun WordRepository() : WordRepository
}