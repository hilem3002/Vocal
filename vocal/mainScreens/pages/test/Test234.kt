package com.example.vocal.mainScreens.pages.test

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vocal.R
import com.example.vocal.viewModels.MainViewModel
import com.example.vocal.viewModels.TestViewModel
import com.example.vocal.viewModels.WordViewModel
import kotlinx.coroutines.delay

@Composable
fun Test234BackGround(
    title : String,
    color : Color,
    painter : Painter,
    blurColor : Color,
    controller : NavController,
    wordViewModel : WordViewModel,
    testViewModel : TestViewModel,
    testId : Int,
    mainViewModel : MainViewModel
) {

    var isNumSelectClicked by remember{ mutableStateOf(false) }
    var isTimeSelectClicked by remember{ mutableStateOf(false) }
    var number by remember{ mutableStateOf("20") }
    var time by remember{ mutableStateOf("10") }
    var prevQuizStatistics by remember{ mutableStateOf(1) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = color),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 36.dp, end = 36.dp, top = 36.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            IconButton(
                onClick = {
                    prevQuizStatistics = 4
                    controller.navigateUp()
                },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = colorResource(id = R.color.dark_white)
                ),
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = colorResource(id = R.color.dark_white),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
            }
        }
        Row (
            modifier = Modifier
                .padding(start = 36.dp, end = 36.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Card (
                modifier = Modifier
                    .fillMaxWidth(0.46f)
                    .padding(top = 12.dp, bottom = 24.dp)
                    .height(48.dp)
                    .clickable {
                        isNumSelectClicked = true
                    },
                colors = CardDefaults.cardColors(
                    contentColor = colorResource(id = R.color.dark_white),
                    containerColor = blurColor
                )
            ) {
                Row (
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp)
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = null
                    )
                    Text(
                        text = "$number kelime",
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.dark_white)
                    )
                }
            }
            Card (
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(top = 12.dp, bottom = 24.dp)
                    .height(48.dp)
                    .clickable {
                        isTimeSelectClicked = true
                    },
                colors = CardDefaults.cardColors(
                    contentColor = colorResource(id = R.color.dark_white),
                    containerColor = blurColor
                )
            ) {
                Row (
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp)
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = null
                    )
                    Text(
                        text = "$time dakika",
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.dark_white)
                    )
                }
            }
        }
        if (prevQuizStatistics == 1) {
            WhiteCardPre234(
                painter = painter,
                color = color,
                isNumSelectClicked = isNumSelectClicked,
                onNumChange = { number = it },
                onNumStateChange = { isNumSelectClicked = it },
                defaultNum = number,
                testId = testId,
                wordViewModel = wordViewModel,
                prevQuizStatistics = {prevQuizStatistics = it},
                defaultTime = time,
                isTimeSelectClicked = isTimeSelectClicked,
                onTimeChange = {time = it},
                onTimeStateChange = {isTimeSelectClicked = it},
                testViewModel = testViewModel,
                mainViewModel = mainViewModel
            )
        }
        else if (prevQuizStatistics == 2) {
            WhiteCardTest234(
                wordViewModel = wordViewModel,
                testViewModel = testViewModel,
                prevQuizStatistics = {prevQuizStatistics = it},
                onClick = {
                    if (it) number = (number.toInt()-1).toString()
                }
            )
        }
        else if (prevQuizStatistics == 3) {
            WhiteCardStatistics234(
                testViewModel = testViewModel,
                wordViewModel = wordViewModel,
                mainViewModel = mainViewModel
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhiteCardPre234(
    painter : Painter,
    color : Color,
    isNumSelectClicked : Boolean,
    isTimeSelectClicked : Boolean,
    onNumChange : (String) -> Unit,
    onNumStateChange : (Boolean) -> Unit,
    onTimeChange : (String) -> Unit,
    onTimeStateChange : (Boolean) -> Unit,
    defaultNum : String,
    defaultTime : String,
    testId : Int,
    wordViewModel : WordViewModel,
    prevQuizStatistics : (Int) -> Unit,
    testViewModel : TestViewModel,
    mainViewModel : MainViewModel
) {

    val testNum = mainViewModel.getTestNum()?.toInt()
    val totalQuestionNum = mainViewModel.getTotalQuestionNum()?.toInt()
    val currentDay = mainViewModel.getCurrentDay()
    val currentDayNum = mainViewModel.getDayWordNum(currentDay+"Num")
    val weeklyNum = mainViewModel.getWeeklyWords()

    Card (
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp)
    ) {
        Column (
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 150.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // question number selection
            if (isNumSelectClicked && !isTimeSelectClicked) {
                var number by remember{ mutableStateOf(defaultNum) }
                Card (
                    modifier = Modifier
                        .height(250.dp)
                        .width(250.dp)
                        .shadow(4.dp, RoundedCornerShape(24.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.onBackground
                    ),
                    shape = RoundedCornerShape(24.dp),
                ) {
                    Column (
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, top = 30.dp, bottom = 30.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxHeight(0.5f)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                IconButton(onClick = { number = (number.toInt()-1).toString() }) {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowDown,
                                        contentDescription = null,
                                        modifier = Modifier.size(36.dp),
                                        tint = MaterialTheme.colorScheme.surface
                                    )
                                }
                                TextField(
                                    value = number,
                                    onValueChange = {number = it},
                                    modifier = Modifier.width(100.dp),
                                    textStyle = LocalTextStyle.current.copy(
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 36.sp
                                    ),
                                    colors = TextFieldDefaults.textFieldColors(
                                        containerColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        textColor = colorResource(id = R.color.blue),
                                        focusedIndicatorColor = colorResource(id = R.color.blue)
                                    ),
                                )
                                IconButton(onClick = { number = (number.toInt()+1).toString() }) {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowUp,
                                        contentDescription = null,
                                        modifier = Modifier.size(36.dp),
                                        tint = MaterialTheme.colorScheme.surface
                                    )
                                }
                            }
                            Text(
                                text = "kelime",
                                fontWeight = FontWeight.Bold,
                                color = colorResource(id = R.color.blue)
                            )
                        }
                        IconButton(onClick = {
                            onNumChange(number)
                            onNumStateChange(false)
                        }) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = colorResource(id = R.color.green),
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }
                }
            }
            else if (isTimeSelectClicked) {
                var time by remember{ mutableStateOf(defaultTime) }
                Card (
                    modifier = Modifier
                        .height(250.dp)
                        .width(250.dp)
                        .shadow(4.dp, RoundedCornerShape(24.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.onBackground
                    ),
                    shape = RoundedCornerShape(24.dp),
                ) {
                    Column (
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, top = 30.dp, bottom = 30.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxHeight(0.5f)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                IconButton(onClick = { time = (time.toInt()-1).toString() }) {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowDown,
                                        contentDescription = null,
                                        modifier = Modifier.size(36.dp),
                                        MaterialTheme.colorScheme.surface
                                    )
                                }
                                TextField(
                                    value = time,
                                    onValueChange = {time = it},
                                    modifier = Modifier.width(100.dp),
                                    textStyle = LocalTextStyle.current.copy(
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 36.sp
                                    ),
                                    colors = TextFieldDefaults.textFieldColors(
                                        containerColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        textColor = colorResource(id = R.color.blue),
                                        focusedIndicatorColor = colorResource(id = R.color.blue)
                                    ),
                                )
                                IconButton(onClick = { time = (time.toInt()+1).toString() }) {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowUp,
                                        contentDescription = null,
                                        modifier = Modifier.size(36.dp),
                                        tint = MaterialTheme.colorScheme.surface
                                    )
                                }
                            }
                            Text(
                                text = "dakika",
                                fontWeight = FontWeight.Bold,
                                color = colorResource(id = R.color.blue)
                            )
                        }
                        IconButton(onClick = {
                            onTimeChange(time)
                            onTimeStateChange(false)
                        }) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = colorResource(id = R.color.green),
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }
                }
            }
            else {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .height(250.dp)
                        .width(250.dp)
                )
            }
            Text(
                text = "Yukarıda öğrenmek istediğin kelime\nsayısını girdikten sonra başlayabilirsin",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.surface
            )
            Button(
                onClick = {
                    if (testNum != null) {
                        mainViewModel.save("testNum", (testNum+1).toString())
                    }
                    if (totalQuestionNum != null) {
                        mainViewModel.save("totalQuestionNum", (totalQuestionNum+defaultNum.toInt()).toString())
                    }

                    // updating the weekly statistics
                    mainViewModel.save(currentDay+"Num", (currentDayNum+defaultNum.toInt()).toString())
                    mainViewModel.save("weeklyWordNum", (weeklyNum+defaultNum.toInt()).toString())

                    // clears the wrong answers num in the view model
                    testViewModel.clearWrong()

                    if (testId == 2) {
                        wordViewModel.getWordsByRandom(defaultNum.toInt())
                    }
                    else if (testId == 3) {
                        wordViewModel.getWordsByDifficulty(
                            difficulty = 1,
                            num = defaultNum.toInt()
                        )
                    }
                    else {
                        wordViewModel.getWordsByDifficulty(
                            difficulty = -1,
                            num = defaultNum.toInt()
                        )
                    }
                    prevQuizStatistics(2)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = color,
                    contentColor = MaterialTheme.colorScheme.background
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Başla",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
                )
            }
        }
    }
}

@Composable
fun WhiteCardTest234(
    wordViewModel : WordViewModel,
    testViewModel : TestViewModel,
    prevQuizStatistics : (Int) -> Unit,
    onClick: (Boolean) -> Unit
) {

    val randomWordList = wordViewModel.randomWordList.collectAsState()
    var questionNum by remember{ mutableStateOf(0) }
    var isAllSetted by remember{ mutableStateOf(false) }
    var passedQuestionIndex by remember{ mutableStateOf(0) }


    if (questionNum < randomWordList.value.size) {
        testViewModel.putAllAnswers(randomWordList.value, questionNum)
        if (questionNum == randomWordList.value.size-1) {
            isAllSetted = true
        }
    }

    Card(
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp)
    ) {
        Column (
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ){

            var isClicked by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                Text(
                    text = testViewModel.testQuestionState,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.surface
                )
                optionRows(
                    options = testViewModel.testOptionsState,
                    onClick = {
                        isClicked = it
                        onClick(it)
                    },
                    questionNum = questionNum,
                    testViewModel = testViewModel
                )
                Text(
                    text = "Sıradaki soruya geçene kadar\nsüre durdurulur",
                    color = MaterialTheme.colorScheme.surface,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.alpha(
                        if (isClicked) 1f else 0f
                    )
                )
            }
            Row (
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (isClicked) {
                    Button(
                        onClick = {
                            if (!isAllSetted) {
                                questionNum++
                            }
                            else if (passedQuestionIndex <= testViewModel.passedQuestionNums.size-1) {
                                questionNum = testViewModel.passedQuestionNums[passedQuestionIndex]
                                passedQuestionIndex++
                            }
                            else {
                                prevQuizStatistics(3)
                            }
                            isClicked = false
                            onClick(false)
                            testViewModel.clearOptions()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onBackground
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        val buttonText by remember{ mutableStateOf(
                            if ((isAllSetted && testViewModel.passedQuestionNums.isEmpty())
                                        ||
                                (passedQuestionIndex == testViewModel.passedQuestionNums.size && testViewModel.passedQuestionNums.isNotEmpty()))
                            "Bitir"
                            else "Sıradaki soru"
                        ) }
                        Text(
                            text = buttonText,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
                        )
                    }
                }
                else {
                    Button(
                        onClick = {
                            isClicked = true
                            onClick(true)
                            testViewModel.wrong++
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.48f)
                            .shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                            contentColor = MaterialTheme.colorScheme.onBackground
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Bilmiyorum",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
                        )
                    }
                    Button(
                        onClick = {
                            if (!isAllSetted) {
                                testViewModel.passedQuestionNums.add(questionNum)
                            }
                            isClicked = true
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.92f)
                            .shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onBackground
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Pas",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun optionRows(
    options: MutableMap<String, Boolean>,
    onClick : (Boolean) -> Unit,
    questionNum : Int,
    testViewModel : TestViewModel
){

    val correctColor = colorResource(id = R.color.green)
    val incorrectColor = colorResource(id = R.color.orange)
    val keysList = options.keys.toList()
    val valList = options.values.toList()

    // clears all states for each question with questionNum
    var cardColor1 by remember(questionNum){ mutableStateOf(Color.LightGray) }
    var cardColor2 by remember(questionNum){ mutableStateOf(Color.LightGray) }
    var cardColor3 by remember(questionNum){ mutableStateOf(Color.LightGray) }
    var cardColor4 by remember(questionNum){ mutableStateOf(Color.LightGray) }
    var isSelected by remember(questionNum){ mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    if (!isSelected) {
                        isSelected = true
                        onClick(isSelected)
                        if (valList[0]) {
                            cardColor1 = correctColor
                        }
                        else {
                            cardColor1 = incorrectColor
                            testViewModel.wrong++
                        }
                    }
                },
            colors = CardDefaults.cardColors(
                containerColor = if (isSelected && valList[0]) correctColor else cardColor1
            )
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = keysList[0],
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        }
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    if (!isSelected) {
                        isSelected = true
                        onClick(isSelected)
                        if (valList[1]) {
                            cardColor2 = correctColor
                        }
                        else {
                            cardColor2 = incorrectColor
                            testViewModel.wrong++
                        }
                    }
                },
            colors = CardDefaults.cardColors(
                containerColor = if (isSelected && valList[1]) correctColor else cardColor2
            )
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = keysList[1],
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        }
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    if (!isSelected) {
                        isSelected = true
                        onClick(isSelected)
                        if (valList[2]) {
                            cardColor3 = correctColor
                        }
                        else {
                            cardColor3 = incorrectColor
                            testViewModel.wrong++
                        }
                    }
                },
            colors = CardDefaults.cardColors(
                containerColor = if (isSelected && valList[2]) correctColor else cardColor3
            )
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = keysList[2],
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        }
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    if (!isSelected) {
                        isSelected = true
                        onClick(isSelected)
                        if (valList[3]) {
                            cardColor4 = correctColor
                        }
                        else {
                            cardColor4 = incorrectColor
                            testViewModel.wrong++
                        }
                    }
                },
            colors = CardDefaults.cardColors(
                containerColor = if (isSelected && valList[3]) correctColor else cardColor4
            )
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = keysList[3],
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        }
    }
}

@Composable
fun WhiteCardStatistics234(
    testViewModel : TestViewModel,
    wordViewModel : WordViewModel,
    mainViewModel : MainViewModel,
) {

    val totalWrongNum = mainViewModel.getTotalWrongNum()?.toInt()
    if (totalWrongNum != null) {
        mainViewModel.save("totalWrongNum", (totalWrongNum+testViewModel.wrong).toString())
    }

    Card (
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp)
    ){
        Column (
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${wordViewModel.randomWordList.value.size} kelime",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.surface
            )
            Row (
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 50.dp)
                    .fillMaxSize(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val correct = wordViewModel.randomWordList.value.size-testViewModel.wrong
                val heightPer2  = if (testViewModel.wrong > 0) 30/wordViewModel.randomWordList.value.size*testViewModel.wrong
                else 0
                AnimatedStatisticCard(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    text = "$correct doğru",
                    height = (30-heightPer2).toFloat()/30f,
                    width = 0.46f
                )
                AnimatedStatisticCard(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    text = "${testViewModel.wrong} yanlış / boş",
                    height = heightPer2.toFloat()/30f,
                    width = 0.85f
                )
            }
        }
    }
}

@Composable
fun AnimatedStatisticCard(
    color : Color,
    text : String,
    height : Float,
    width : Float
) {

    var expanded by remember{ mutableStateOf(false) }
    val statisticColumn by animateFloatAsState(
        targetValue = if (expanded) height+0.05f else 0.05f,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )

    // animation delay
    LaunchedEffect(Unit) {
        delay(1000L)
        expanded = true
    }

    Card (
        modifier = Modifier
            .fillMaxWidth(width)
            .fillMaxHeight(statisticColumn),
        colors = CardDefaults.cardColors(
            containerColor = color
        ),
    ) {
        Box (
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
@Preview
fun Test234BackgroundPreview() {
    /*Test234BackGround(
        title = "Listende olan keliemlerle çalış",
        color = colorResource(id = R.color.green),
        painter = painterResource(id = R.drawable.green_pre_img),
        blurColor = colorResource(id = R.color.blured_green)
    )*/
}
