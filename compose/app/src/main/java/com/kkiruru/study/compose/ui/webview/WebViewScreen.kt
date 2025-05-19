package com.kkiruru.study.compose.ui.webview

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class WebViewScreen {
}




@Composable
fun StoreScreen(
    index: Int,
) {

    LaunchedEffect(Unit) {
        Log.e("OtherScreen", "StoreScreen  LaunchedEffect")
    }

    DisposableEffect(Unit) {
        onDispose {
            Log.e("OtherScreen", "StoreScreen  DisposableEffect  onDispose")
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "StoreScreen $index"
        )
    }
}



@Composable
fun MainScreen(
    index: Int,
) {

    LaunchedEffect(Unit) {
        Log.e("OtherScreen", "MainScreen  LaunchedEffect")
    }

    DisposableEffect(Unit) {
        onDispose {
            Log.e("OtherScreen", "MainScreen  DisposableEffect  onDispose")
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "MainScreen"
        )
    }
}



@Composable
fun LaundryScreen(
    index: Int,
) {

    LaunchedEffect(Unit) {
        Log.e("OtherScreen", "LaundryScreen  LaunchedEffect")
    }

    DisposableEffect(Unit) {
        onDispose {
            Log.e("OtherScreen", "LaundryScreen  DisposableEffect  onDispose")
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "LaundryScreen $index"
        )
    }
}


@Composable
fun MyScreen(
    index: Int,
) {

    LaunchedEffect(Unit) {
        Log.e("OtherScreen", "MyScreen  LaunchedEffect")
    }

    DisposableEffect(Unit) {
        onDispose {
            Log.e("OtherScreen", "MyScreen  DisposableEffect  onDispose")
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "MyScreen $index"
        )
    }
}

