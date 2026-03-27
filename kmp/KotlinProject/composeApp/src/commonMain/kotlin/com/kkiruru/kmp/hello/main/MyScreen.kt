package com.kkiruru.kmp.hello.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kkiruru.kmp.hello.core.utils.LogUtil

@Composable
fun MyScreen() {
    WebView(
        modifier = Modifier.fillMaxSize(),
//        url = "https://m.naver.com",
        url = "https://v2webapp.laundry24.kr/my-page",
        onMessage = { message ->
            LogUtil.d("message: ${message}")
        },
    )
}