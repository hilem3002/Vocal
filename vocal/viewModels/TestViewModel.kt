package com.example.vocal.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.vocal.Screen
import com.example.vocal.data.Graph
import com.example.vocal.data.models.Word
import com.example.vocal.data.services.MainService
import kotlin.random.Random
import kotlin.random.nextInt

class TestViewModel(private val service : MainService = Graph.mainService) : ViewModel() {

    private val _currentScreen : MutableState<Screen> = mutableStateOf(Screen.TestScreen.MainTestScreen)

    var testQuestionState by mutableStateOf("")
    val testOptionsState : MutableMap<String, Boolean> = mutableStateMapOf(
        "1" to false,
        "2" to false,
        "3" to false,
        "4" to false
    )
    val passedQuestionNums : MutableList<Int> = mutableListOf()
    var wrong by mutableStateOf(0)

    fun putAllAnswers(randomWordList : List<Word>, questionNum : Int) {
        if (randomWordList.isNotEmpty()) {
            testQuestionState = randomWordList[questionNum].text
            val correctAns = Random.nextInt(1,5)
            val randomNums : MutableList<Int> = mutableListOf(questionNum)
            var range = (randomWordList.indices).filter { it !in randomNums }
            val ans1Num = range.random()
            randomNums.add(ans1Num)
            range = (randomWordList.indices).filter { it !in randomNums }
            val ans2Num = range.random()
            randomNums.add(ans2Num)
            range = (randomWordList.indices).filter { it !in randomNums }
            val ans3Num = range.random()
            randomNums.add(ans3Num)
            testOptionsState.clear()
            when(correctAns) {
                1 -> {
                    testOptionsState[randomWordList[questionNum].meaning] = true
                    testOptionsState[randomWordList[ans1Num].meaning] = false
                    testOptionsState[randomWordList[ans2Num].meaning] = false
                    testOptionsState[randomWordList[ans3Num].meaning] = false
                }
                2 -> {
                    testOptionsState[randomWordList[ans1Num].meaning] = false
                    testOptionsState[randomWordList[questionNum].meaning] = true
                    testOptionsState[randomWordList[ans2Num].meaning] = false
                    testOptionsState[randomWordList[ans3Num].meaning] = false
                }
                3 -> {
                    testOptionsState[randomWordList[ans1Num].meaning] = false
                    testOptionsState[randomWordList[ans2Num].meaning] = false
                    testOptionsState[randomWordList[questionNum].meaning] = true
                    testOptionsState[randomWordList[ans3Num].meaning] = false
                }
                4 -> {
                    testOptionsState[randomWordList[ans1Num].meaning] = false
                    testOptionsState[randomWordList[ans2Num].meaning] = false
                    testOptionsState[randomWordList[ans3Num].meaning] = false
                    testOptionsState[randomWordList[questionNum].meaning] = true
                }
            }
            Log.d("1", testOptionsState.toString())
            Log.d("2", randomNums.toString())
        }
    }

    fun clearOptions() {
        testOptionsState.clear()
        testOptionsState["1"] = false
        testOptionsState["2"] = false
        testOptionsState["3"] = false
        testOptionsState["4"] = false
    }

    fun clearWrong() {
        wrong = 0
    }

    val currentScreen
        get() = _currentScreen

}

