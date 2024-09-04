package com.kkiruru.study.compose.performance

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class PerformanceActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PerformanceApp()
            }
        }
    }
}

@Composable
fun PerformanceApp(
    modifier: Modifier = Modifier,
) {
    Log.e("PerformanceActivity", "PerformanceApp")
    MainScreen()
}

@Composable
fun MainScreen() {
    Log.e("PerformanceActivity", "MainScreen")
    Box {
        PerformanceTestScreen()
    }
}

@Composable
fun PerformanceTestScreen(modifier: Modifier = Modifier) {
    Log.e("PerformanceActivity", "PerformanceTestScreen")

    var bgColor1 by remember { mutableStateOf(Color.Black) }
    var bgColor2 by remember { mutableStateOf(Color.Red) }

    Box() {
        Column(modifier = Modifier) {
            Row(modifier = Modifier) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(100.dp)
                        .background(bgColor1) //배경색 칠함
                )
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(100.dp)
                        .background(bgColor2) //배경색 칠함
                )
            }

            Row(modifier = Modifier) {
                Button(
                    onClick = {
                        // 클릭시 색상 토글
                        bgColor1 = if (bgColor1 == Color.Black) Color.LightGray
                        else Color.Black
                    },
                    modifier = Modifier
                ) {
                    Text("Box1 Color")
                }

                Button(
                    onClick = {
                        // 클릭시 색상 토글
                        bgColor2 = if (bgColor2 == Color.Red) Color.Blue
                        else Color.Red
                    },
                    modifier = Modifier
                ) {
                    Text("Box2 Color")
                }
            }
        }
    }
}