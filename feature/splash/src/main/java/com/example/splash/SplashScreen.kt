package com.example.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.component.WeatherBackground

@Composable
fun SplashScreen() {

    WeatherBackground(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SplashScreen_Logo()
            Spacer(modifier = Modifier.height(10.dp))
            SplashScreen_AppName()
            Spacer(modifier = Modifier.height(10.dp))
            SplashScreen_Text()
        }
    }

}

@Composable
private fun SplashScreen_Logo() {
    Icon(painter = painterResource(id = R.drawable.ic_main_logo), contentDescription = null, Modifier.size(200.dp))
}

@Composable
private fun SplashScreen_AppName() {
    Text(text = stringResource(id = R.string.app_name), fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
}

@Composable
private fun SplashScreen_Text() {
    Text(text = stringResource(id = R.string.text_logo), minLines = 2)
}