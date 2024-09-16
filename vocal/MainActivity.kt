package com.example.vocal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.vocal.data.Graph
import com.example.vocal.data.services.SharedPreferencesService
import com.example.vocal.data.repositories.SharedPreferencesRepository
import com.example.vocal.data.factories.MainStartFactory
import com.example.vocal.data.factories.MainFactory
import com.example.vocal.data.factories.StartFactory
import com.example.vocal.ui.theme.VocalTheme
import com.example.vocal.viewModels.MainStartViewModel
import com.example.vocal.viewModels.MainViewModel
import com.example.vocal.viewModels.StartViewModel

class MainActivity : ComponentActivity() {
    private lateinit var mainStartViewModel : MainStartViewModel
    private lateinit var startViewModel : StartViewModel
    private lateinit var mainViewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Graph.provide(this)
        installSplashScreen()
        // setting up the view models
        val sharedPreferenceRepository = SharedPreferencesRepository(this)
        val sharedPreferenceService = SharedPreferencesService(sharedPreferenceRepository)
        val mainStartFactory = MainStartFactory(sharedPreferenceService)
        val startFactory = StartFactory(sharedPreferenceService);
        val mainFactory = MainFactory(sharedPreferenceService)
        mainStartViewModel = ViewModelProvider(this, mainStartFactory)[MainStartViewModel::class.java]
        startViewModel = ViewModelProvider(this, startFactory)[StartViewModel::class.java]
        mainViewModel = ViewModelProvider(this, mainFactory)[MainViewModel::class.java]
        setContent {
            VocalTheme(mainViewModel = mainViewModel) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainStartView(mainStartViewModel, startViewModel, mainViewModel)
                }
            }
        }
    }
}
