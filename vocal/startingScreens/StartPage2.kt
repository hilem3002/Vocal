package com.example.vocal.startingScreens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vocal.R
import com.example.vocal.Screen
import com.example.vocal.viewModels.StartViewModel

@Composable
fun StartPage2(navController : NavController, startViewModel : StartViewModel) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.dark_white)
            )
            .padding(
                start = 10.dp,
                end = 10.dp,
                top = 20.dp,
                bottom = 20.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("Öğrenmek ")
                }
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("istediğin\n")
                }
                withStyle(style = SpanStyle(color = colorResource(id = R.color.orange))) {
                    append("dili ")
                }
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("seç!")
                }
            },
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            textAlign = TextAlign.Center
        )

        var isClickedSpain by remember { mutableStateOf(false) }
        var isClickedUk by remember { mutableStateOf(false) }
        var isClickedGermany by remember { mutableStateOf(false) }
        var language by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ){
            LanguageSelectionRow(
                flagIcon = painterResource(id = R.drawable.spain_flag),
                onClick = {
                    isClickedSpain = true
                    isClickedUk = false
                    isClickedGermany = false
                    language = "spanish"
                },
                isClicked = isClickedSpain
            )
            LanguageSelectionRow(
                flagIcon = painterResource(id = R.drawable.uk_flag),
                onClick = {
                    isClickedSpain = false
                    isClickedUk = true
                    isClickedGermany = false
                    language = "english"
                },
                isClicked = isClickedUk
            )
            LanguageSelectionRow(
                flagIcon = painterResource(id = R.drawable.germany_flag),
                onClick = {
                    isClickedSpain = false
                    isClickedUk = false
                    isClickedGermany = true
                    language = "german"
                },
                isClicked = isClickedGermany
            )
        }
        Text(
            text = "Bunu daha sonra ayarlardan\n değiştirebilirsin",
            fontSize = 16.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 20.dp)
        )
        val context = LocalContext.current
        Button(
            onClick = {
                // looking for is a language selected or not
                if (language == "") {
                    Toast.makeText(context, "lütfen öğrenmek istediğiniz dili seçin", Toast.LENGTH_LONG).show()
                }
                else {
                    startViewModel.save(language)
                    navController.navigate(Screen.BottomStartScreen.BottomScreen.route)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
                .shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.blue),
                contentColor = colorResource(id = R.color.dark_white)
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Dili seç",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
            )
        }
    }
}

@Composable
fun LanguageSelectionRow(
    flagIcon : Painter,
    onClick : () -> Unit,
    isClicked : Boolean
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        IconButton(
            modifier = Modifier
                .size(70.dp),
            onClick = onClick,
        ) {
            Image(
                painter = flagIcon,
                modifier = Modifier.fillMaxSize(),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        Image(
            painter = painterResource(id = R.drawable.baseline_circle_2_green),
            contentDescription = null,
            modifier = Modifier.alpha(if (isClicked) 1f else 0f))
    }
}

@Preview
@Composable
fun PreviewStartPage2() {
    val controller : NavController = rememberNavController()
    //StartPage2(controller)
}