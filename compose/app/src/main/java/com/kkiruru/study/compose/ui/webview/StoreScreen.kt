package com.kkiruru.study.compose.ui.webview

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun StoreScreenRoute(
//    webView: WebView,
    index: Int,
) {
    StoreScreen(
//        webView = webView,
        onLoadedPage = {},
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StoreScreen(
//    webView: WebView,
//    url: String = "",
    onLoadedPage: () -> Unit,
    chromeClient: WebChromeClient = remember { object : WebChromeClient() {} },
    client: WebViewClient = remember { object : WebViewClient() {} },
){


    val url by rememberUpdatedState(newValue = "https://m.naver.com/")

    val password by rememberSaveable { mutableStateOf("https://m.naver.com/") }


    val context = LocalContext.current
//    val webView = LocalWebOwner.current
    val webView = rememberWebView()
//
    LaunchedEffect(key1 = password) {
        Log.e("", "LaunchedEffect ${password}")
        webView?.loadUrl(url)
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { webView },
    )
//
//
//    Box {
//        webView?.let {
//            AndroidView(
////            modifier = Modifier.fillMaxSize(),
//                factory = { context ->
//                    webView
//                }
//            )
//        }
//    }


//    BoxWithConstraints(
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//        // WebView changes it's layout strategy based on
//        // it's layoutParams. We convert from Compose Modifier to
//        // layout params here.
//        val width = if (constraints.hasFixedWidth) {
//            ViewGroup.LayoutParams.MATCH_PARENT
//        } else {
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        }
//        val height = if (constraints.hasFixedHeight) {
//            ViewGroup.LayoutParams.MATCH_PARENT
//        } else {
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        }
//
//        val layoutParams = FrameLayout.LayoutParams(
//            width,
//            height
//        )
//
////        var webView by remember { mutableStateOf<WebView?>(null) }
//
//
//        LaunchedEffect(key1 = Unit) {
//            webView?.loadUrl("https://m.naver.com/")
//        }
//        AndroidView(
//            modifier = Modifier.fillMaxSize(),
//            factory = { webView }
//        )
//        AndroidView(
//            factory = {
//                webView
//            },
//            update = {
//                Log.i("TEMP", "AndroidView update - $webView")
//            },
//            onRelease = { parentFrame ->
//                Log.i("TEMP", "AndroidView onRelease - $webView")
//                (parentFrame.children.first() as? WebView)?.let {
//                    parentFrame.removeView(it)
//                }
//            }
//        )
    }
//}

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
    val webView = remember(key) {
        WebView(context).apply {
            loadUrl("https://m.naver.com")
        }
    }

    WebViewInternal(
        modifier = modifier,
        webView = webView,
        canGoBack = canGoBack,
        onDispose = {
            webView.saveState(savedBundle)
            webView.destroy()
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