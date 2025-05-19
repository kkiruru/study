package com.kkiruru.study.compose.ui.webview

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Bundle
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun StoreScreenRoute(
) {
    StoreScreen(
        key = "store",
        onLoadedPage = {},
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StoreScreen(
    key: String,
    onLoadedPage: () -> Unit,
    chromeClient: WebChromeClient = remember { object : WebChromeClient() {} },
    client: WebViewClient = remember { object : WebViewClient() {} },
){
    val webView = LocalWebOwner.current

    var canGoBack by remember {
        mutableStateOf(false)
    }

    BackHandler(canGoBack) {
        webView?.goBack()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        webView?.let {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = {
                    webView
                },
            )
        }
//        WebView(
//            key = "store",
//        )

    }

//    val savedBundle: Bundle = rememberSaveable(key) { Bundle() }
//    var canGoBack by remember {
//        mutableStateOf(true)
//    }
//    webView?.let {
//        WebViewInternal(
//            modifier = Modifier.fillMaxSize(),
//            webView = it,
//            canGoBack = canGoBack,
//            onDispose = {
//                webView.saveState(savedBundle)
//                webView.destroy()
//            }
//        )
//    }
}

@Composable
fun WebView(
    key: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    val savedBundle: Bundle = rememberSaveable(key) { Bundle() }
    var canGoBack by remember {
        mutableStateOf(false)
    }
//    val webView = remember(key) {
//        WebView(context).apply {
//            loadUrl("https://m.naver.com")
//        }
//    }

    WebViewInternal(
        modifier = modifier,
        webView = rememberWebView(),
        canGoBack = canGoBack,
        onDispose = {
//            webView.saveState(savedBundle)
//            webView.destroy()
        }
    )
}

@Composable
fun WebViewInternal(
    webView: WebView,
    canGoBack: Boolean,
    onDispose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DisposableEffect(webView) {
        onDispose(onDispose)
    }

    BackHandler(canGoBack) {
        webView.goBack()
    }

    AndroidView(
        modifier = modifier,
        factory = { webView }
    )
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun rememberWebView(): WebView {
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
            webViewClient = object : WebViewClient() {
                override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                    handler?.proceed()
                }
            }
            loadUrl("https://m.naver.com")
        }
    }
    return webView
}