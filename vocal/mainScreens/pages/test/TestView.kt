package com.example.vocal.mainScreens.pages.test

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vocal.R
import com.example.vocal.Screen
import com.example.vocal.viewModels.MainViewModel
import com.example.vocal.viewModels.TestViewModel
import com.example.vocal.viewModels.WordViewModel

@Composable
fun TestView(
    testViewModel : TestViewModel,
    wordViewModel : WordViewModel,
    mainViewModel : MainViewModel
) {

    val controller : NavController = rememberNavController()

    NavigationTest(
        controller = controller,
        testViewModel = testViewModel,
        wordViewModel = wordViewModel,
        mainViewModel = mainViewModel
    )

}

@Composable
fun NavigationTest(
    controller : NavController,
    testViewModel : TestViewModel,
    wordViewModel : WordViewModel,
    mainViewModel : MainViewModel
) {
    NavHost(
        navController = controller as NavHostController,
        startDestination = testViewModel.currentScreen.value.route,
        enterTransition = { EnterTransition.None},
        exitTransition = { ExitTransition.None}
    ) {
        composable(Screen.TestScreen.MainTestScreen.route) {
            Test(controller = controller)
        }
        composable(Screen.TestScreen.specified1TestScreen.route) {
            Test1Background(controller = controller)
        }
        composable(Screen.TestScreen.specified2TestScreen.route) {
            Test234BackGround(
                title = "Listende olan kelimelerle çalış",
                color = colorResource(id = R.color.green),
                painter = painterResource(id = R.drawable.green_pre_img),
                blurColor = colorResource(id = R.color.blured_green),
                controller = controller,
                wordViewModel = wordViewModel,
                testId = 2,
                testViewModel = testViewModel,
                mainViewModel = mainViewModel
            )
        }
        composable(Screen.TestScreen.specified3TestScreen.route) {
            Test234BackGround(
                title = "Zorlandığın kelimelerle çalış",
                color = colorResource(id = R.color.orange),
                painter = painterResource(id = R.drawable.orange_pre_img),
                blurColor = colorResource(id = R.color.blured_orange),
                controller = controller,
                wordViewModel = wordViewModel,
                testId = 3,
                testViewModel = testViewModel,
                mainViewModel = mainViewModel
            )
        }
        composable(Screen.TestScreen.specified4TestScreen.route) {
            Test234BackGround(
                title = "Bildiğin kelimeleri tekrar et",
                color = colorResource(id = R.color.blue),
                painter = painterResource(id = R.drawable.blue_pre_img),
                blurColor = colorResource(id = R.color.blured_blue),
                controller = controller,
                wordViewModel = wordViewModel,
                testId = 4,
                testViewModel = testViewModel,
                mainViewModel = mainViewModel
            )
        }
    }
}