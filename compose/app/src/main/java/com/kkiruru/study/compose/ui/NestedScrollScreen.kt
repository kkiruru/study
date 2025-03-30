package com.kkiruru.study.compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun NestedScrollRoute() {
    NestedScrollScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NestedScrollScreen()
{
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("GFG | Nested Scrolling", color = Color.White) },
//                backgroundColor = Color(0xff0f9d58)
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .verticalScroll(rememberScrollState())
                        .padding(32.dp)
                ) {
                    Column {
                        repeat(6) {
                            Box(
                                modifier = Modifier
                                    .height(128.dp)
                                    .verticalScroll(rememberScrollState())
                            ) {
                                Text(
                                    text = "Scroll here",
                                    modifier = Modifier
                                        .border(12.dp, Color.DarkGray)
                                        .padding(24.dp)
                                        .height(150.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    )

}

