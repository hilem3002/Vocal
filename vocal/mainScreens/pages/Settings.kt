package com.example.vocal.mainScreens.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vocal.R
import com.example.vocal.viewModels.MainViewModel

@Composable
fun Settings(mainViewModel : MainViewModel) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.blue)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        WhiteSettingsCard(mainViewModel)
    }
}

@Composable
fun WhiteSettingsCard(mainViewModel : MainViewModel) {
    Box(
        modifier = Modifier
            .fillMaxHeight(0.8f)
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(topEnd = 48.dp, topStart = 48.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Vocal",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.padding(top =  20.dp)
                )
                Card (
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .shadow(4.dp, RoundedCornerShape(24.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.onBackground
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Row (
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth()
                    ){
                        Text(
                            text = "Bildirimler",
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray,
                            fontSize = 16.sp
                        )

                        var isSwitched by remember{ mutableStateOf(mainViewModel.isNotificationsOn()) }

                        Switch(
                            checked = isSwitched,
                            onCheckedChange = {
                                isSwitched = it
                                mainViewModel.save("notifications", if (it) "on" else "off")
                            },
                            colors = SwitchDefaults.colors(
                                checkedTrackColor = colorResource(id = R.color.green),
                                uncheckedTrackColor = colorResource(id = R.color.orange),
                                uncheckedBorderColor = Color.Transparent,
                                uncheckedThumbColor = Color.White
                            )
                        )
                    }
                    Row (
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                            .fillMaxWidth()
                    ){
                        Text(
                            text = "Karanlık tema",
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray,
                            fontSize = 16.sp
                        )

                        var isSwitched by remember{ mutableStateOf(mainViewModel.isDarkThemeOnState.value) }

                        Switch(
                            checked = isSwitched,
                            onCheckedChange = {
                                isSwitched = it
                                mainViewModel.save("darkTheme", if (it) "on" else "off")
                                mainViewModel.isDarkThemeOnState.value = it
                            },
                            colors = SwitchDefaults.colors(
                                checkedTrackColor = colorResource(id = R.color.green),
                                uncheckedTrackColor = colorResource(id = R.color.orange),
                                uncheckedBorderColor = Color.Transparent,
                                uncheckedThumbColor = Color.White
                            )
                        )

                    }
                }
                Card (
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .shadow(4.dp, RoundedCornerShape(24.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.onBackground
                    ),
                    shape = RoundedCornerShape(24.dp)
                ){
                    Column (
                        Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "Öğrermek istediğin dil",
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray,
                            fontSize = 16.sp
                        )

                        var language by remember{ mutableStateOf(mainViewModel.languageSelection()) }

                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column (
                                modifier = Modifier.wrapContentHeight(),
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = if (isSystemInDarkTheme() || mainViewModel.isDarkThemeOnState.value)
                                        painterResource(id = R.drawable.spain_flag_dark)
                                                else
                                        painterResource(id = R.drawable.spain_flag),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(56.dp)
                                        .width(56.dp)
                                        .clickable {
                                            language = "spanish"
                                            mainViewModel.save("language", language!!)
                                        }
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_circle_2_green),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(top = 10.dp)
                                        .alpha(alpha = if (language.equals("spanish")) 1f else 0f)
                                )
                            }
                            Column (
                                modifier = Modifier.wrapContentHeight(),
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = if(isSystemInDarkTheme() || mainViewModel.isDarkThemeOnState.value)
                                        painterResource(id = R.drawable.uk_flag_dark)
                                                else
                                        painterResource(id = R.drawable.uk_flag),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(56.dp)
                                        .width(56.dp)
                                        .clickable {
                                            language = "english"
                                            mainViewModel.save("language", language!!)
                                        }
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_circle_2_green),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(top = 10.dp)
                                        .alpha(alpha = if (language.equals("english")) 1f else 0f)
                                )
                            }
                            Column (
                                modifier = Modifier.wrapContentHeight(),
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = if(isSystemInDarkTheme() || mainViewModel.isDarkThemeOnState.value)
                                        painterResource(id = R.drawable.german_flag_dark)
                                                else
                                        painterResource(id = R.drawable.germany_flag),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(56.dp)
                                        .width(56.dp)
                                        .clickable {
                                            language = "german"
                                            mainViewModel.save("language", language!!)
                                        }
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_circle_2_green),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(top = 10.dp)
                                        .alpha(alpha = if (language.equals("german")) 1f else 0f)
                                )
                            }
                        }
                    }
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.settings_logo),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-60).dp)
                .shadow(4.dp, CircleShape)
        )
    }
}

@Composable
@Preview
fun SettingsPreview() {
    //Settings()
}