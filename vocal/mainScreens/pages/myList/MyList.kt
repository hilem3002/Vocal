package com.example.vocal.mainScreens.pages.myList

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vocal.R
import com.example.vocal.Screen
import com.example.vocal.data.models.Word
import com.example.vocal.viewModels.MainViewModel
import com.example.vocal.viewModels.WordViewModel

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MyList(
    wordViewModel : WordViewModel,
    controller : NavController,
    mainViewModel : MainViewModel
) {

    var searchText by remember { mutableStateOf("") }
    val wordList = wordViewModel.allWords.collectAsState(initial = listOf())
    val searchedWords = wordViewModel.searchedWords.collectAsState()

    Scaffold(
        content = {
            Column (
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(start = 24.dp, end = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                TextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        wordViewModel.getWordsByPrefix(searchText)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder  = { Text(
                        text = "kelime",
                        color = colorResource(id = R.color.dark_white),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold)},
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = colorResource(id = R.color.blue),
                        textColor = colorResource(id = R.color.dark_white),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedTrailingIconColor = colorResource(id = R.color.dark_white),
                        disabledTrailingIconColor = colorResource(id = R.color.dark_white),
                        unfocusedTrailingIconColor = colorResource(id = R.color.dark_white)
                    ),
                    textStyle = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    ),
                    shape = RoundedCornerShape(24.dp),
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    },
                    singleLine = true
                )

                val wordNum = mainViewModel.getWordNum()?.toInt()

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 24.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    items(
                        items = if (searchText.equals(""))
                                wordList.value.reversed()
                                else searchedWords.value.reversed(),
                        key = { word -> word.id}
                    ) { word ->
                        val dismissState = rememberDismissState(
                            confirmStateChange = {
                                if (it == DismissValue.DismissedToStart) {
                                    wordViewModel.deleteWord(word)
                                    if (wordNum != null) {
                                        mainViewModel.save("wordNum", (wordNum-1).toString())
                                    }
                                }
                                else {
                                    wordViewModel.wordIdState = word.id
                                    wordViewModel.wordTextState = word.text
                                    wordViewModel.wordMeaningState = word.meaning
                                    wordViewModel.wordDifficultyState = word.difficulty
                                    controller.navigate(Screen.MyListScreen.UpdateListScreen.route)
                                }
                                true
                            }
                        )

                        // deleting the word when swipped end to start, editting the word when swipped to opposite way
                        SwipeToDismiss(
                            state = dismissState,
                            background = {
                                val color by animateColorAsState(
                                    targetValue =
                                    if (dismissState.dismissDirection == DismissDirection.EndToStart)
                                        colorResource(id = R.color.orange)
                                    else if (dismissState.dismissDirection == DismissDirection.StartToEnd)
                                        colorResource(id = R.color.green)
                                    else Color.Transparent,
                                    label = ""
                                )
                                val deleteOrEdit by mutableStateOf(
                                    color == colorResource(id = R.color.green)
                                )
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .padding(top = 12.dp)
                                            .fillMaxSize()
                                            .background(
                                                color = color,
                                                shape = RoundedCornerShape(24.dp)
                                            ),
                                        contentAlignment =
                                            if (deleteOrEdit)
                                                Alignment.CenterStart
                                            else
                                                Alignment.CenterEnd

                                    ) {
                                        Icon(
                                            imageVector =
                                                if (deleteOrEdit)
                                                    Icons.Default.Edit
                                                else
                                                    Icons.Default.Delete,
                                            contentDescription = null,
                                            tint = colorResource(id = R.color.dark_white),
                                            modifier = Modifier
                                                .padding(
                                                    start = if (deleteOrEdit) 20.dp else 0.dp,
                                                    end = if (!deleteOrEdit) 20.dp else 0.dp
                                                )
                                        )
                                    }
                                }
                            },
                            directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
                            dismissThresholds = {FractionalThreshold(0.25f)},
                        ) {
                            WordCard(
                                word = word,
                                wordViewModel = wordViewModel
                            )
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    controller.navigate(Screen.MyListScreen.AddMyListScreen.route)
                },
                modifier = Modifier
                    .padding(end = 24.dp, bottom = 24.dp),
                containerColor = colorResource(id = R.color.green),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    )
}

@Composable
fun WordCard(
    word : Word,
    wordViewModel : WordViewModel
) {

    var executedText by remember{ mutableStateOf(word.text) }
    var executedDifficulty by remember{ mutableStateOf(word.difficulty) }
    val executedImageOrange by remember{ derivedStateOf{
        if (executedDifficulty == 0 || executedDifficulty == -1)
            R.drawable.outline_circle_24_orange
        else
            R.drawable.baseline_circle_24_orange
    } }
    val executedImageGreem by remember { derivedStateOf{
        if (executedDifficulty == 0 || executedDifficulty == 1)
            R.drawable.outline_circle_24_green
        else
            R.drawable.baseline_circle_2_green
    } }

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(145.dp)
            .padding(top = 12.dp)
            .shadow(4.dp, RoundedCornerShape(24.dp))
            // for each click it changes the executed text
            .clickable { executedText = if (executedText == word.text) word.meaning else word.text },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onBackground),
        shape = RoundedCornerShape(24.dp)
    ) {
        Row (
            modifier = Modifier
                .padding(36.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = executedText,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.fillMaxWidth(0.5f)
            )
            Row (
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = executedImageOrange),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        // for each click it changes the word card selection status
                        executedDifficulty =
                            if (executedDifficulty == 0 || executedDifficulty == -1) 1
                            else 0
                        wordViewModel.updateWord(
                            Word(
                                id = word.id,
                                text = word.text,
                                meaning = word.meaning,
                                difficulty = executedDifficulty
                            )
                        )
                    }
                )
                Image(
                    painter = painterResource(id = executedImageGreem),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        // for each click it changes the word card selection status
                        executedDifficulty =
                            if (executedDifficulty == 0 || executedDifficulty == 1) -1
                            else 0
                        wordViewModel.updateWord(
                            Word(
                                id = word.id,
                                text = word.text,
                                meaning = word.meaning,
                                difficulty = executedDifficulty
                            )
                        )
                    }
                )
            }
            Image(painter = painterResource(
                id = R.drawable.outline_touch_app_24_green),
                contentDescription = null
            )
        }
    }
}

@Composable
@Preview
fun MyListPreview() {
    //MyList()
}