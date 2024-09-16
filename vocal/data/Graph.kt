package com.example.vocal.data

import android.content.Context
import androidx.room.Room
import com.example.vocal.data.services.MainService

object Graph {
    lateinit var database : Database

    val mainService by lazy {
        MainService(database.WordRepository())
    }

    fun provide(context : Context) {
        database = Room.databaseBuilder(context, Database::class.java, "words.db").build()
    }
}