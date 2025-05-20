package com.kkiruru.study.compose.ui.webview

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver


@Composable
fun StoreScreenRoute(
) {
    StoreScreen(
        onLoadedPage = {
            Log.e("StoreScreen", ">>>>> onLoadedPage")
        },
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StoreScreen(
    onLoadedPage: () -> Unit,
){
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val webViewOwner = LocalWebOwner.current
    val webView = remember { webViewOwner }

    BackHandler {
        if (webView?.canGoBack() == true) {
            webView.goBack()
        }
    }

    LaunchedEffect(Unit) {
        Log.e("StoreScreen", "StoreScreen  onLoadedPage")
        onLoadedPage.invoke()
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            Log.e("StoreScreen", "StoreScreen  LifecycleEventObserver ${event}")
            when (event) {
                Lifecycle.Event.ON_DESTROY -> {
                    webView?.destroy()
                }
                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    webView?.let {
        AndroidView(
            factory = { webView },
            modifier = Modifier.fillMaxSize()
        )
    }
}