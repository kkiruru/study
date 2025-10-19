package com.kkiruru.kmp.hello.main

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
actual fun WebView(modifier: Modifier, url: String) {
    AndroidView(
        factory = {
            WebView(it).apply {
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        },
        modifier = modifier
    )
}
