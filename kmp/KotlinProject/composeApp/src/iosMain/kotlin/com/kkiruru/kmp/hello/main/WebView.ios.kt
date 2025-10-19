package com.kkiruru.kmp.hello.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.WebKit.WKWebView

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun WebView(modifier: Modifier, url: String) {
    UIKitView(
        factory = {
            WKWebView()
        },
        modifier = modifier,
        update = { webView ->
            NSURL.URLWithString(url)?.let {
                val request = NSURLRequest.requestWithURL(it)
                webView.loadRequest(request)
            }
        }
    )
}
