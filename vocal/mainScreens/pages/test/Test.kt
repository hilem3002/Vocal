package com.example.vocal.mainScreens.pages.test

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vocal.R
import com.example.vocal.Screen

@Composable
fun Test(controller : NavController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ){
        TestTypeCard(
            title = "Yeni kelimeler\nöğren",
            description = "Listende olan veya olmayan rastgele\nkelimeler ile çalış",
            painter = painterResource(id = R.drawable.test_1_img),
            color = colorResource(id = R.color.blue),
            onClick = {
                controller.navigate(Screen.TestScreen.specified1TestScreen.route)
            }
        )
        TestTypeCard(
            title = "Listende olan\nkelimelerle çalış",
            description = "Listende bulunan kelimelerle pratik\nyap, kelimeleri unutma",
            painter = painterResource(id = R.drawable.test_2_img),
            color = colorResource(id = R.color.green),
            onClick = {
                controller.navigate(Screen.TestScreen.specified2TestScreen.route)
            }
        )
        TestTypeCard(
            title = "Zorlandığın\nkelimeleri çalış",
            description = "Listende bulunan ve öğrenmekte zorluk\nçektiğin, zor olarak işaretlediğin kelimeler ile çalış",
            painter = painterResource(id = R.drawable.test_3_img),
            color = colorResource(id = R.color.orange),
            onClick = {
                controller.navigate(Screen.TestScreen.specified3TestScreen.route)
            }
        )
        TestTypeCard(
            title = "Bildiğin kelimeleri\ntekrar et",
            description = "Listende bulunan ve öğrendim olarak\nişaretlediğin kelimelerle çalış",
            painter = painterResource(id = R.drawable.test_4_img),
            color = colorResource(id = R.color.blue),
            onClick = {
                controller.navigate(Screen.TestScreen.specified4TestScreen.route)
            }
        )
    }
}

@Composable
fun TestTypeCard(
    title : String,
    description : String,
    painter : Painter,
    color : Color,
    onClick : () -> Unit
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .shadow(6.dp, RoundedCornerShape(48.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(48.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth()
            )
            Text(
                text = title,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.dark_white),
                textAlign = TextAlign.Center
            )
            Text(
                text = description,
                fontSize = 12.sp,
                color = colorResource(id = R.color.dark_white),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview
fun TestPreview() {
    //Test()
}