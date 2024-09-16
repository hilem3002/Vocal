package com.example.vocal.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocal.Screen
import com.example.vocal.data.services.SharedPreferencesService
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Locale

class MainViewModel(private val service : SharedPreferencesService) : ViewModel() {

    private val _currentScreen : MutableState<Screen> = mutableStateOf(Screen.BottomScreen.Home)
    var isDarkThemeOnState = mutableStateOf(isDarkThemeOn())

    val currentScreen : MutableState<Screen>
        get() = _currentScreen

    fun languageSelection() : String? {
        return service.getSharedPreference("language")
    }

    fun save(key : String, value : String) {
        service.saveSharedPreference(key, value);
    }

    fun isNotificationsOn() : Boolean {
        return when (service.getSharedPreference("notifications")) {
            "empty", "off" -> false
            else -> true
        }
    }

    fun isDarkThemeOn() : Boolean {
        return when (service.getSharedPreference("darkTheme")) {
            "empty", "off" -> false
            else -> true
        }
    }

    fun getWordNum() : String? {
        val num = service.getSharedPreference("wordNum")
        return if (num.equals("empty")) "0" else num
    }

    fun getTestNum() : String? {
        val num = service.getSharedPreference("testNum")
        return if (num.equals("empty")) "0" else num
    }

    fun getTotalQuestionNum() : String? {
        val num = service.getSharedPreference("totalQuestionNum")
        return if (num.equals("empty")) "0" else num
    }

    fun getTotalWrongNum() : String? {
        val num = service.getSharedPreference("totalWrongNum")
        return if (num.equals("empty")) "0" else num
    }

    fun getSuccessRate() : String {
        val totalQuestionNum = service.getSharedPreference("totalQuestionNum")?.toIntOrNull() ?: 0
        val totalWrongNum = service.getSharedPreference("totalWrongNum")?.toIntOrNull() ?: 0
        var rate = "%100"
        if (totalQuestionNum != 0) {
            rate = "%"+((totalQuestionNum-totalWrongNum).toFloat()/totalQuestionNum.toFloat()*100).toInt()
        }
        return rate
    }

    fun getWeeklyWords() : Int {
        val num = service.getSharedPreference("weeklyWordNum")?.toIntOrNull() ?: 0;
        return num
    }

    fun getDayWordNum(key : String) : Int {
        val num = service.getSharedPreference(key)?.toIntOrNull() ?: 0;
        return num
    }

    fun getDailyStatistic(key : String) : Float {
        if (getWeeklyWords() == 0) return 0f
        return String
            .format("%.2f", getDayWordNum(key).toFloat()/getWeeklyWords().toFloat())
            .toFloat()
    }

    fun getCurrentDay() : String {
        return LocalDate.now().dayOfWeek.toString().lowercase(Locale.ENGLISH)
    }

    fun getIsStatisticsCleared() : Boolean {
        val isCleared = service.getSharedPreference("isStatisticsCleared")
        return isCleared.equals("true")
    }

    fun clearsAllWeekStatistics() {
        save("weeklyWordNum", "0")
        save("mondayNum", "0")
        save("tuesdayNum", "0")
        save("wednesdayNum", "0")
        save("thursdayNum", "0")
        save("fridayNum", "0")
        save("saturdayNum", "0")
        save("sundayNum", "0")
        save("isStatisticsCleared", "true")
    }

    fun maxDayNum() : Int {
        val list = listOf<Int>(
            getDayWordNum("mondayNum"),
            getDayWordNum("tuesdayNum"),
            getDayWordNum("wednesdayNum"),
            getDayWordNum("thursdayNum"),
            getDayWordNum("fridayNum"),
            getDayWordNum("saturdayNum"),
            getDayWordNum("sundayNum")
        )
        return list.max()
    }
}