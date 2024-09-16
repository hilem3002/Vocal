package com.example.vocal.mainScreens.pages

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vocal.R
import com.example.vocal.viewModels.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun Home(mainViewModel : MainViewModel) {

    val wordNum = mainViewModel.getWordNum()
    val testNum = mainViewModel.getTestNum()
    val successRate = mainViewModel.getSuccessRate()

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        DailyVocabularyCard(word = "la silla", meaning = "sandalye")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Özet",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.surface
            )
            Card (
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(145.dp)
                    .shadow(3.dp, RoundedCornerShape(24.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onBackground
                ),
            ){
                Row (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(40.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column (
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (wordNum != null) {
                            Text(
                                text = wordNum,
                                color = MaterialTheme.colorScheme.surface,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = "listede",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                    Column (
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (testNum != null) {
                            Text(
                                text = testNum,
                                color = MaterialTheme.colorScheme.surface,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = "test",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                    Column (
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = successRate,
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "başarı",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                }
            }
            WeeklyStatisticsCard(mainViewModel = mainViewModel)
        }
    }
}

@Composable
fun DailyVocabularyCard(
    word : String,
    meaning : String,
) {

    var executedText by remember {mutableStateOf(word)}

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable {
                // for each click it changes the executed text
                executedText = if (executedText == word) meaning else word
            },
        colors = CardDefaults.cardColors(
            colorResource(id = R.color.blue)
        ),
        shape = RoundedCornerShape(48.dp)
    ) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 30.dp, end = 30.dp, bottom = 40.dp)
                .size(42.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.add_symbol_svgrepo_com),
                contentDescription = null,
                modifier = Modifier.fillMaxSize())
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ){
                Text(
                    text = executedText,
                    color = colorResource(id = R.color.dark_white),
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold
                )
                Image(
                    painter = painterResource(id = R.drawable.outline_touch_app_24_white),
                    contentDescription = null
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp)
                    .shadow(elevation = 6.dp, shape = RoundedCornerShape(48.dp)),
                shape = RoundedCornerShape(48.dp)
            ) {
                Row (
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.onBackground,
                            containerColor = colorResource(id = R.color.orange)
                        ),
                        shape = RoundedCornerShape(
                            topStart = 48.dp,
                            bottomStart = 48.dp
                        ),
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.5f)
                    ) {
                        Text(
                            text = "geri",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                        )
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.onBackground,
                            containerColor = colorResource(id = R.color.green)
                        ),
                        shape = RoundedCornerShape(
                            topEnd = 48.dp,
                            bottomEnd = 48.dp
                        ),
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "ileri",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WeeklyStatisticsCard(
    mainViewModel : MainViewModel
) {

    var weeklyWords by remember{ mutableStateOf(mainViewModel.getWeeklyWords()) }

    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp)
            .shadow(3.dp, RoundedCornerShape(24.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Column (
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Hafta",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.blue)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = colorResource(id = R.color.blue),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold)
                        ) {
                            append("$weeklyWords ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        ) {
                            append("kelime")
                        }
                    }
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                val maxWordNum = mainViewModel.maxDayNum()
                val mondayNum = mainViewModel.getDayWordNum("mondayNum")
                val tuesdayNum = mainViewModel.getDayWordNum("tuesdayNum")
                val wednesdayNum = mainViewModel.getDayWordNum("wednesdayNum")
                val thursdayNum = mainViewModel.getDayWordNum("thursdayNum")
                val fridayNum = mainViewModel.getDayWordNum("fridayNum")
                val saturdayNum = mainViewModel.getDayWordNum("saturdayNum")
                val sundayNum = mainViewModel.getDayWordNum("sundayNum")

                DayStatisticColumn(
                    day = "Pzt",
                    height = mainViewModel.getDailyStatistic("mondayNum"),
                    isMax = mondayNum == maxWordNum,
                    dailyWordNum = mondayNum,
                    onClick = {weeklyWords = it}
                )
                DayStatisticColumn(
                    day = "Sal",
                    height = mainViewModel.getDailyStatistic("tuesdayNum"),
                    isMax = tuesdayNum == maxWordNum,
                    dailyWordNum = tuesdayNum,
                    onClick = {weeklyWords = it}
                )
                DayStatisticColumn(
                    day = "Çar",
                    height = mainViewModel.getDailyStatistic("wednesdayNum"),
                    isMax = wednesdayNum == maxWordNum,
                    dailyWordNum = wednesdayNum,
                    onClick = {weeklyWords = it}
                )
                DayStatisticColumn(
                    day = "Per",
                    height = mainViewModel.getDailyStatistic("thursdayNum"),
                    isMax = thursdayNum == maxWordNum,
                    dailyWordNum = thursdayNum,
                    onClick = {weeklyWords = it}
                )
                DayStatisticColumn(
                    day = "Cum",
                    height = mainViewModel.getDailyStatistic("fridayNum"),
                    isMax = fridayNum == maxWordNum,
                    dailyWordNum = fridayNum,
                    onClick = {weeklyWords = it}
                )
                DayStatisticColumn(
                    day = "Cmt",
                    height = mainViewModel.getDailyStatistic("saturdayNum"),
                    isMax = saturdayNum == maxWordNum,
                    dailyWordNum = saturdayNum,
                    onClick = {weeklyWords = it}
                )
                DayStatisticColumn(
                    day = "Paz",
                    height = mainViewModel.getDailyStatistic("sundayNum"),
                    isMax = sundayNum == maxWordNum,
                    dailyWordNum = sundayNum,
                    onClick = {weeklyWords = it}
                )
            }
        }
    }
}

@Composable
fun DayStatisticColumn(
    day : String,
    height : Float,
    isMax : Boolean,
    dailyWordNum : Int,
    onClick : (Int) -> Unit
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

    Column (
        modifier = Modifier
            .fillMaxHeight()
            .width(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(16.dp))
                .clickable {
                    onClick(dailyWordNum)
                },
            contentAlignment = Alignment.BottomCenter
        ) {
            Card (
                modifier = Modifier
                    .padding(start = 4.dp, end = 4.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(statisticColumn),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isMax) colorResource(id = R.color.green) else colorResource(id = R.color.orange)
                )
            ) {}
        }
        Text(
            text = day,
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(top = 18.dp),
        )
    }
}

@Composable
@Preview
fun HomePreview() {
    //DayStatisticColumn(day = "Pzt")
}