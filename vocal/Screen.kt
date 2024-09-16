package com.example.vocal

import androidx.annotation.DrawableRes

sealed class Screen(val title : String, val route : String) {

    sealed class BottomScreen(
        val bTitle : String, val bRoute : String, @DrawableRes val icon : Int
    ) : Screen(bTitle, bRoute) {
        object Home : BottomScreen("vocal", "home", R.drawable.baseline_home_24)
        object MyList : BottomScreen("listem", "myList", R.drawable.baseline_library_books_24)
        object Test : BottomScreen("test", "test", R.drawable.baseline_question_answer_24)
        object Settings : BottomScreen("ayarlar", "settings", R.drawable.baseline_settings_24)
    }

    sealed class StartingScreen(
        val sTitle : String, val sRoute : String
    ) : Screen(sTitle, sRoute) {
        object Start1 : StartingScreen("start1", "start1")
        object Start2 : StartingScreen("start2", "start2")
    }

    sealed class BottomStartScreen(
        val bsTitle : String, val bsRoute : String
    ) : Screen(bsTitle, bsRoute) {
        object StartingScreen : BottomStartScreen("start", "start")
        object BottomScreen : BottomStartScreen("bottom", "bottom")
    }

    sealed class MyListScreen(
        val mlTitle : String, val mlRoute : String
    ) : Screen(mlTitle, mlRoute) {
        object MainMyListScreen : MyListScreen("mainMyList", "mainMyList")
        object AddMyListScreen : MyListScreen("ekle", "addMyList")
        object UpdateListScreen : MyListScreen("düzenle", "updateMyList")
    }

    sealed class TestScreen(
        val tTitle : String, val tRoute : String
    ) : Screen(tTitle, tRoute) {
        object MainTestScreen : TestScreen("mainTest", "mainTest")
        object specified1TestScreen : TestScreen("specified1Test", "specified1Test")
        object specified2TestScreen : TestScreen("specified2Test", "specified2Test")
        object specified3TestScreen : TestScreen("specified3Test", "specified3Test")
        object specified4TestScreen : TestScreen("specified4Test", "specified4Test")
    }
}

val screensInBottom = listOf(
    Screen.BottomScreen.Home,
    Screen.BottomScreen.MyList,
    Screen.BottomScreen.Test,
    Screen.BottomScreen.Settings
)
