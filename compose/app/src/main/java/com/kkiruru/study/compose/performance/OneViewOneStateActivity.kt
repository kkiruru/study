package com.kkiruru.study.compose.performance

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

/*
//  https://proandroiddev.com/improve-performance-in-jetpack-compose-via-one-view-one-state-pattern-ec9d808f4eaf
 */


class OneViewOneStateActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel = hiltViewModel<OneViewOneStateViewModel>()
            val state by mainViewModel.state.collectAsState()

            LaunchedEffect(key1 = Unit) {
                mainViewModel.processAction(MainAction.ContinueData)
            }

            Data(state)
        }
    }
}

@Composable
fun Data(state: MainState) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxSize()
    ) {
        ContinueUpdateText(state.text)
        PlayerA(state.playerAText, state.listA)
        PlayerB(state.playerBText, state.listB)
        PlayerC(state.playerCText, state.listC)
    }
}

@Composable
fun ContinueUpdateText(value: String) {
    Text(text = value, fontSize = 12.sp, color = Color.Black)
}

@Composable
fun PlayerA(value: String, list: List<Int>) {
    LaunchedEffect(key1 = value) {
        Log.d("PlayerA", "Recomposed with value: $value, $list")
    }

    Text(text = value, fontSize = 12.sp, color = Color.Magenta)
}


@Composable
fun PlayerB(value: String, list: List<Int>) {
    LaunchedEffect(key1 = value) {
        Log.d("PlayerB", "Recomposed with value: $value, $list")
    }

    Text(text = value, fontSize = 12.sp, color = Color.Magenta)
}

@Composable
fun PlayerC(value: String, list: List<Int>) {
    LaunchedEffect(key1 = value) {
        Log.d("PlayerC", "Recomposed with value: $value, $list")
    }

    Text(text = value, fontSize = 12.sp, color = Color.Magenta)
}