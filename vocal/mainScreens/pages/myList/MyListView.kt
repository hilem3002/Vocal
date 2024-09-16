package com.example.vocal.mainScreens.pages.myList

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vocal.Screen
import com.example.vocal.viewModels.MainViewModel
import com.example.vocal.viewModels.MyListViewModel
import com.example.vocal.viewModels.WordViewModel

@Composable
fun MyListView(
    wordViewModel : WordViewModel,
    myListViewModel : MyListViewModel,
    mainViewModel : MainViewModel
) {

    val controller : NavController = rememberNavController()

    NavigationMyList(
        controller = controller,
        wordViewModel = wordViewModel,
        myListViewModel = myListViewModel,
        mainViewModel = mainViewModel
    )

}

@Composable
fun NavigationMyList(
    controller : NavController,
    wordViewModel : WordViewModel,
    myListViewModel : MyListViewModel,
    mainViewModel : MainViewModel
) {
    NavHost(
        navController = controller as NavHostController,
        startDestination = myListViewModel.currentScreen.value.route,
        enterTransition = { EnterTransition.None},
        exitTransition = { ExitTransition.None}
    ) {
        composable(Screen.MyListScreen.MainMyListScreen.route) {
            MyList(wordViewModel = wordViewModel, controller = controller, mainViewModel = mainViewModel)
        }
        composable(Screen.MyListScreen.AddMyListScreen.route) {
            AddWord(wordViewModel = wordViewModel, controller = controller, mainViewModel = mainViewModel)
        }
        composable(Screen.MyListScreen.UpdateListScreen.route) {
            EditWord(wordViewModel = wordViewModel, controller = controller)
        }
    }
}