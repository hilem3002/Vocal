package com.example.vocal.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.vocal.data.services.SharedPreferencesService
import com.example.vocal.Screen

class MainStartViewModel(private val service : SharedPreferencesService) : ViewModel() {

    private val _currentScreen : MutableState<Screen> = mutableStateOf(Screen.BottomStartScreen.StartingScreen)

    val currentScreen : MutableState<Screen>
        get() = _currentScreen

    fun setCurrentScreen(screen : Screen) {
        _currentScreen.value = screen
    }

    fun isAppActivatedBefore(): Boolean {
        return !service.getSharedPreference("isActivatedBefore").equals("empty")
    }
}