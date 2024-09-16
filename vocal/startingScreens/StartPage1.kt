package com.example.vocal.startingScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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

@Composable
fun StartPage1(navController : NavController) {
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
            text = "vocal",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.blue)
        )
        Image(
            painter = painterResource(id = R.drawable.start_screen_img),
            contentDescription = null
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append("Yeni ")
                    }
                    withStyle(style = SpanStyle(color = colorResource(id = R.color.orange))) {
                        append("kelimeler\n")
                    }
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append("öğren!")
                    }
                },
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "İstediğin dilde yeni kelimeler öğren, kendi\n kelime listeni oluştur!",
                fontSize = 16.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 20.dp)
            )
        }
        Button(
            onClick = {navController.navigate(Screen.StartingScreen.Start2.route)},
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
                text = "Hadi başlayalım",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewStartPage1() {
    val controller : NavController = rememberNavController()
    StartPage1(controller)
}