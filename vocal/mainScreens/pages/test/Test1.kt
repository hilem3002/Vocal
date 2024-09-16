package com.example.vocal.mainScreens.pages.test

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vocal.R

@Composable
fun Test1Background(controller : NavController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.blue)),
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
                onClick = { controller.navigateUp() },
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
                    text = "Yeni kelimeler öğren",
                    color = colorResource(id = R.color.dark_white),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
            }
        }
        Card (
            modifier = Modifier
                .fillMaxWidth(0.35f)
                .padding(top = 12.dp, bottom = 24.dp)
                .height(48.dp)
                .clickable { TODO() },
            colors = CardDefaults.cardColors(
                contentColor = colorResource(id = R.color.dark_white),
                containerColor = colorResource(id = R.color.blured_blue)
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
                    text = "20 kelime",
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.dark_white)
                )
            }
        }
        WhiteCardPre1()
        //WhiteCardTest1()
    }
}

@Composable
fun WhiteCardPre1() {
    Card (
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.dark_white)
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
            Image(
                painter = painterResource(id = R.drawable.blue_pre_img),
                contentDescription = null,
                modifier = Modifier
                    .height(250.dp)
                    .width(250.dp)
            )
            Text(
                text = "Yukarıda öğrenmek istediğin kelime\nsayısını girdikten sonra başlayabilirsin",
                textAlign = TextAlign.Center,
                color = Color.Black
            )
            Button(
                onClick = { /*TODO*/ },
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
fun WhiteCardTest1() {
    Card (
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.dark_white)
        ),
        shape = RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp)
    ){
        Column (
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 150.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Column (
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Kelime",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 48.sp
                )
                Text(
                    text = "Anlamı",
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.blue),
                    fontSize = 36.sp
                )
            }
            Column (
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth(0.48f)
                            .shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.orange),
                            contentColor = colorResource(id = R.color.dark_white)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Geri",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
                        )
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth(0.92f)
                            .shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.green),
                            contentColor = colorResource(id = R.color.dark_white)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "İleri",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
                        )
                    }
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.blue),
                        contentColor = colorResource(id = R.color.dark_white)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Listeme ekle",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun Test1BackgroundReview() {
    //Test1Background()
}