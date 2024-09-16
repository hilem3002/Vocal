package com.example.vocal.mainScreens.pages.myList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vocal.R
import com.example.vocal.data.models.Word
import com.example.vocal.viewModels.MainViewModel
import com.example.vocal.viewModels.WordViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWord(
    wordViewModel : WordViewModel,
    controller : NavController,
    mainViewModel : MainViewModel
) {

    val wordNum = mainViewModel.getWordNum()?.toInt()
    val currentDay = mainViewModel.getCurrentDay()
    val currentDayNum = mainViewModel.getDayWordNum(currentDay+"Num")
    val weeklyNum = mainViewModel.getWeeklyWords()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .padding(start = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            TextField(
                value = wordViewModel.wordTextState,
                onValueChange = {wordViewModel.wordTextState = it},
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(
                    text = "kelime",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.surface
                )},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = MaterialTheme.colorScheme.surface
                ),
                textStyle = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.surface
                )
            )
            TextField(
                value = wordViewModel.wordMeaningState,
                onValueChange = {wordViewModel.wordMeaningState = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                placeholder = { Text(
                    text = "Anlamı",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primaryContainer
                )},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer
                ),
                textStyle = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    controller.navigateUp()
                    wordViewModel.cleanStates()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth(0.48f)
                    .height(80.dp)
                    .shadow(2.dp, RoundedCornerShape(16.dp))
            ) {
                Text(
                    text = "Vazgeç",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
            }
            Button(
                onClick = {
                    wordViewModel.addWord(
                        Word(
                            text = wordViewModel.wordTextState,
                            meaning = wordViewModel.wordMeaningState,
                            difficulty = wordViewModel.wordDifficultyState
                        )
                    )
                    if (wordNum != null) {
                        mainViewModel.save("wordNum", (wordNum+1).toString())
                    }
                    mainViewModel.save(currentDay+"Num", (currentDayNum+1).toString())
                    mainViewModel.save("weeklyWordNum", (weeklyNum+1).toString())
                    controller.navigateUp()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth(0.92f)
                    .height(80.dp)
                    .shadow(2.dp, RoundedCornerShape(16.dp))
            ) {
                Text(
                    text = "Ekle",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
            }
        }
    }
}

@Composable
@Preview
fun AddWordPreview() {
    //AddWord()
}