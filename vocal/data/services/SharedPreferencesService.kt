package com.example.vocal.data.services

import com.example.vocal.data.repositories.SharedPreferencesRepository

class SharedPreferencesService(private val repository : SharedPreferencesRepository) {

    fun getSharedPreference(key : String) : String? {
        return repository.getSharedPreference(key)
    }

    fun saveSharedPreference(key : String, value : String) {
        repository.saveSharedPreference(key, value)
    }

}