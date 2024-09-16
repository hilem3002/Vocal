package com.example.vocal.startingScreens

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vocal.Screen
import com.example.vocal.viewModels.StartViewModel

@Composable
fun StartView(navController : NavController, startViewModel : StartViewModel) {

    val controller : NavController = rememberNavController()

    NavigationStart(
        navController = controller,
        navControllerBridge = navController,
        startViewModel = startViewModel
    )

}

@Composable
fun NavigationStart(
    navController : NavController,
    navControllerBridge : NavController,
    startViewModel : StartViewModel
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = startViewModel.currentScreen.value.route,
        enterTransition = { EnterTransition.None},
        exitTransition = { ExitTransition.None}
    ) {
        composable(Screen.StartingScreen.Start1.route) {
            StartPage1(navController)
        }
        composable(Screen.StartingScreen.Start2.route) {
            StartPage2(navControllerBridge, startViewModel)
        }
    }
}


@Composable
@Preview
fun StartViewPreview() {
    val controller : NavController = rememberNavController()
    //StartView(controller)
}