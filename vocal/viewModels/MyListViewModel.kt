package com.example.vocal.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.vocal.Screen

class MyListViewModel : ViewModel() {

    private val _currentScreen : MutableState<Screen> = mutableStateOf(Screen.MyListScreen.MainMyListScreen)

    val currentScreen
        get() = _currentScreen

}