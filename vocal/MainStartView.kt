package com.example.vocal

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vocal.mainScreens.pages.MainView
import com.example.vocal.startingScreens.StartView
import com.example.vocal.viewModels.MainStartViewModel
import com.example.vocal.viewModels.MainViewModel
import com.example.vocal.viewModels.StartViewModel

@Composable
fun MainStartView (
    mainStartViewModel : MainStartViewModel,
    startViewModel : StartViewModel,
    mainViewModel : MainViewModel
) {

    val controller : NavController = rememberNavController()

    NavigationMainStart(
        navController = controller,
        mainStartViewModel = mainStartViewModel,
        startViewModel = startViewModel,
        mainViewModel = mainViewModel
    )

}

@Composable
fun NavigationMainStart(
    navController : NavController,
    mainStartViewModel : MainStartViewModel,
    startViewModel : StartViewModel,
    mainViewModel : MainViewModel
) {

    // if app has activated before need to jump into to bottomScreen
    val isAppActivatedBefore : Boolean = mainStartViewModel.isAppActivatedBefore()
    if (isAppActivatedBefore) mainStartViewModel.setCurrentScreen(Screen.BottomStartScreen.BottomScreen)

    NavHost(
        navController = navController as NavHostController,
        startDestination = mainStartViewModel.currentScreen.value.route,
        enterTransition = { EnterTransition.None},
        exitTransition = { ExitTransition.None}
    ) {
        composable(Screen.BottomStartScreen.StartingScreen.route) {
            StartView(navController, startViewModel)
        }
        composable(Screen.BottomStartScreen.BottomScreen.route) {
            MainView(mainViewModel)
        }
    }
}