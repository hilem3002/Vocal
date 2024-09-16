package com.example.vocal.data.repositories

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesRepository(context : Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    fun getSharedPreference(key : String) : String? {
        return sharedPreferences.getString(key, "empty")
    }

    fun saveSharedPreference(key : String, value : String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

}