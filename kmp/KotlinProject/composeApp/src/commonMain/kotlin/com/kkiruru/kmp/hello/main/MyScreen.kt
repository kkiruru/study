package com.kkiruru.kmp.hello.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyScreen() {
    WebView(
        modifier = Modifier.fillMaxSize(),
        url = "https://www.google.com"
    )
}
