package com.example.vocal.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("word-table")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo("text")
    val text : String,
    @ColumnInfo("meaning")
    val meaning : String,
    @ColumnInfo("difficulty")
    val difficulty : Int,
)


