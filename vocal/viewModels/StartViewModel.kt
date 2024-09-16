package com.example.vocal.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.vocal.data.services.SharedPreferencesService
import com.example.vocal.Screen

class StartViewModel(private val service : SharedPreferencesService) : ViewModel() {

    private val _currentScreen : MutableState<Screen> = mutableStateOf(Screen.StartingScreen.Start1)

    val currentScreen : MutableState<Screen>
        get() = _currentScreen

    fun save(language : String) {
        service.saveSharedPreference("isActivatedBefore", "true")
        service.saveSharedPreference("language", language);
    }
}