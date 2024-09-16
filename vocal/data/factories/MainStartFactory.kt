package com.example.vocal.data.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vocal.data.services.SharedPreferencesService
import com.example.vocal.viewModels.MainStartViewModel

class MainStartFactory(private val service : SharedPreferencesService) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainStartViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainStartViewModel(service) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}