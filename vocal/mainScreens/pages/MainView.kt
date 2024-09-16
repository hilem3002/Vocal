package com.example.vocal.mainScreens.pages

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vocal.R
import com.example.vocal.Screen
import com.example.vocal.mainScreens.pages.myList.MyList
import com.example.vocal.mainScreens.pages.myList.MyListView
import com.example.vocal.mainScreens.pages.test.Test
import com.example.vocal.mainScreens.pages.test.TestView
import com.example.vocal.screensInBottom
import com.example.vocal.viewModels.MainViewModel
import com.example.vocal.viewModels.MyListViewModel
import com.example.vocal.viewModels.TestViewModel
import com.example.vocal.viewModels.WordViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(mainViewModel : MainViewModel) {

    val controller : NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentScreen = remember{mainViewModel.currentScreen.value}
    var title by remember{ mutableStateOf(currentScreen.title) }
    val isSelectedColor = MaterialTheme.colorScheme.primaryContainer
    val wordViewModel = WordViewModel()
    val myListViewModel = MyListViewModel()
    val testViewModel = TestViewModel()

    // clears all weekly statistics on monday
    val currentDay = mainViewModel.getCurrentDay()
    val isCleared = mainViewModel.getIsStatisticsCleared()
    if (currentDay == "monday") {
        if (!isCleared) {
            mainViewModel.clearsAllWeekStatistics()
        }
    }
    else {
        mainViewModel.save("isStatisticsCleared", "false")
    }

    val bottomBar : @Composable () -> Unit = {
        BottomNavigation (
            backgroundColor = MaterialTheme.colorScheme.onBackground
        ) {
            screensInBottom.forEach { item ->
                val isSelected = currentRoute == item.bRoute
                val tint = if (isSelected) isSelectedColor else Color.Gray
                BottomNavigationItem(
                    selected = currentRoute == item.bRoute,
                    onClick = {
                        title = item.bTitle
                        controller.navigate(item.bRoute)
                    },
                    icon = { Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null,
                        tint = tint
                    ) },
                    selectedContentColor = Color.White
                )
            }
        }
    }

    Scaffold (
        topBar = {TopAppBar(
            title = {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp)
                )},
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        )},
        bottomBar = bottomBar
    ){
        NavigationBottom(
            navController = controller,
            mainViewModel = mainViewModel,
            pd = it,
            wordViewModel = wordViewModel,
            myListViewModel = myListViewModel,
            testViewModel = testViewModel
        )
    }
}

@Composable
fun NavigationBottom(
    navController : NavController,
    mainViewModel : MainViewModel,
    pd : PaddingValues,
    wordViewModel : WordViewModel,
    myListViewModel : MyListViewModel,
    testViewModel : TestViewModel
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = mainViewModel.currentScreen.value.route,
        modifier = Modifier.padding(pd),
        enterTransition = {EnterTransition.None},
        exitTransition = {ExitTransition.None}
    ) {
        composable(Screen.BottomScreen.Home.route) {
            Home(mainViewModel)
        }
        composable(Screen.BottomScreen.MyList.route) {
            MyListView(wordViewModel = wordViewModel, myListViewModel = myListViewModel, mainViewModel = mainViewModel)
        }
        composable(Screen.BottomScreen.Test.route) {
            TestView(testViewModel = testViewModel, wordViewModel = wordViewModel, mainViewModel = mainViewModel)
        }
        composable(Screen.BottomScreen.Settings.route) {
            Settings(mainViewModel)
        }
    }
}

@Composable
@Preview
fun MainViewPreview() {
    //MainView()
}