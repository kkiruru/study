package com.kkiruru.study.compose.ui

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.http.SslError
import android.util.Log
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.launch


// WebViewActivity.kt에서 참조한 LocalWebOwner는 여기서는 직접 관리하므로 제거합니다.
// import com.kkiruru.study.compose.ui.webview.LocalWebOwner
@Composable
fun BottomSheetOnWebViewScreenRoute() {
    BottomSheetOnWebViewScreen()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomSheetOnWebViewScreen(
) {
    val coroutineScope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    var openAlertDialog = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxWidth(),
    ) {
        MainContentContainer(
            onClick = {
                coroutineScope.launch {
                    Log.e(">>>>>>>", ">>>>>>>  sheetState.show()")
                    showBottomSheet = true
//                    sheetState.show()
                }
            },

        )
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight(),
            sheetState = sheetState,
            onDismissRequest = {
                Log.e(">>>>>>>", ">>>>>>>  onDismissRequest")
                showBottomSheet = false
            }
        ) {
            BottomSheetScreen(
                onClickDialog = {
                    coroutineScope.launch {
                        Log.e(">>>>>>>", ">>>>>>>  onClickClose")
                        sheetState.hide()
                    }.invokeOnCompletion {
                        Log.e(">>>>>>>", ">>>>>>>  invokeOnCompletion")
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                        openAlertDialog.value = true
                    }
                }
            )
        }
    }

    when {
        openAlertDialog.value -> {
            AlertDialogExample(
                onDismissRequest = { openAlertDialog.value = false },
                onConfirmation = {
                    openAlertDialog.value = false
                    println("Confirmation registered") // Add logic here to handle confirmation.
                },
                dialogTitle = "Alert dialog example",
                dialogText = "This is an example of an alert dialog with buttons.",
                icon = Icons.Default.Info
            )
        }
    }

}

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainContentContainer(
    onClick: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    ConstraintLayout(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
    ) {
        val (webview, footer) = createRefs()
        Box(modifier = Modifier
            .constrainAs(webview) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(footer.top)
            }

        ) {
            WebViewScreen(urlToLoad = "https://m.naver.com")
        }

        Column (
            modifier = Modifier
                .constrainAs(footer) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = Color(0xFFFFFFFF))
        ) {
            Button(
                modifier = Modifier.padding(vertical = 20.dp),
                onClick = {
                coroutineScope.launch {
                    onClick.invoke()
                }
            }) {
                Text("Click to show sheet")
            }
        }
    }
}


@Composable
private fun BottomSheetScreen(
    onClickDialog : () -> Unit,
) {
    LazyColumn {
        // the first item that is visible
        item {
            Box(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
                    .background(color = Color.White)
            ) {
                Text(
                    text = "Swipe up to Expand the sheet",
                    modifier = Modifier.align(alignment = Alignment.Center),
                    color = Color.Black
                )
            }

            Button(
                modifier = Modifier.padding(vertical = 20.dp),
                onClick = {
                    onClickDialog.invoke()
                }) {
                Text("Click to show Dialog")
            }
        }

        item {
            Box(
                modifier = Modifier
                    .height(156.dp)
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primary)
            ) {
                LazyColumn {
                    items(count = 5) {

                    }
                }
            }
        }

        item {
            Box(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = "하단 UI",
                    modifier = Modifier.align(alignment = Alignment.Center),
                    color = Color.White
                )
            }
        }
    }
}



@SuppressLint("SetJavaScriptEnabled")
@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
) // ExperimentalMaterial3Api 추가 (Scaffold 등 사용 시)
@Composable
private fun WebViewScreen(
    urlToLoad: String,
) {
    val context = LocalContext.current
    var webView: WebView? by remember { mutableStateOf(null) } // webView 상태 관리
    var isLoading by remember { mutableStateOf(true) } // 로딩 상태 관리
    var pageTitle by remember { mutableStateOf("") } // 페이지 제목 관리 (선택 사항)

    // 웹뷰 인스턴스 생성 및 설정 (remember를 사용하여 인스턴스 유지)
    val currentWebView = remember {
        WebView(context).apply {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                // 추가적인 웹뷰 설정 (예: UserAgent, Cache 등)
                // userAgentString = "CustomUserAgent/1.0"
                // cacheMode = WebSettings.LOAD_DEFAULT
            }
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    isLoading = true
                    Log.d("WebView", "Page started loading: $url")
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    isLoading = false
                    pageTitle = view?.title ?: ""
                    Log.d("WebView", "Page finished loading: $url")
                }

                override fun onReceivedSslError(
                    view: WebView?,
                    handler: SslErrorHandler?,
                    error: SslError?
                ) {
                    // SSL 에러 처리 (WebViewActivity 참조)
                    // 주의: 프로덕션 환경에서는 모든 SSL 에러를 무시하는 것은 보안상 위험할 수 있습니다.
                    // 특정 조건에서만 proceed 하거나 사용자에게 알리는 것이 좋습니다.
                    Log.w("WebView", "SSL Error: ${error?.primaryError}, URL: ${error?.url}")
                    handler?.proceed() // SSL 에러 무시하고 진행 (개발용)
                    // handler?.cancel() // SSL 에러 시 로딩 중단
                }

                // 필요시 추가적인 WebViewClient 콜백 구현
                // override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                //     val url = request?.url.toString()
                //     Log.d("WebView", "shouldOverrideUrlLoading: $url")
                //     // 특정 URL 스킴 처리 등
                //     return super.shouldOverrideUrlLoading(view, request)
                // }
            }
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    // 로딩 진행률 처리 (예: ProgressBar 업데이트)
                    Log.d("WebView", "Loading progress: $newProgress%")
                }

                override fun onReceivedTitle(view: WebView?, title: String?) {
                    super.onReceivedTitle(view, title)
                    title?.let { pageTitle = it }
                }
                // 필요시 추가적인 WebChromeClient 콜백 구현 (예: 파일 업로드, alert, confirm 등)
            }
            loadUrl(urlToLoad)
        }.also {
            webView = it // 생성된 웹뷰를 상태에 할당
        }
    }

    // 뒤로가기 처리
    BackHandler(enabled = webView?.canGoBack() == true) {
        webView?.goBack()
    }

    // Lifecycle 관찰
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner, currentWebView) {
        val observer = LifecycleEventObserver { _, event ->
            Log.d(
                "WebViewScreen",
                "LifecycleEventObserver event: ${event.name} for WebView: $currentWebView"
            )
            when (event) {
                Lifecycle.Event.ON_RESUME -> currentWebView.onResume()
                Lifecycle.Event.ON_PAUSE -> currentWebView.onPause()
                Lifecycle.Event.ON_DESTROY -> {
                    // currentWebView의 destroy는 AndroidView의 onRelease에서 처리될 수 있으나,
                    // 명시적으로 호출하거나, AndroidView 밖에서 관리한다면 여기서 destroy
                    // 이 예제에서는 AndroidView의 factory와 update 스코프 내에서 관리되므로
                    // onRelease 콜백에서 처리하는 것이 더 일반적일 수 있습니다.
                    // 그러나 만약 WebView를 remember 외부에서 생성하거나 다른 방식으로 관리한다면
                    // 여기서 destroy하는 것이 맞습니다.
                    // currentWebView.destroy() // AndroidView의 onRelease에서 처리하도록 주석 처리
                }

                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            Log.d(
                "WebViewScreen",
                "DisposableEffect onDispose for WebView: $currentWebView, removing observer"
            )
            lifecycleOwner.lifecycle.removeObserver(observer)
            // WebView의 destroy는 AndroidView의 onRelease에서 호출하는 것이 좋습니다.
            // currentWebView.destroy() // AndroidView의 onRelease에서 처리하도록 주석 처리
        }
    }

    // UI 구성
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AndroidView(
                factory = {
                    // factory는 Composable이 처음 Composition에 추가될 때 호출됩니다.
                    // 여기서 웹뷰 인스턴스를 반환합니다.
                    Log.d(
                        "WebViewScreen",
                        "AndroidView factory called, returning WebView: $currentWebView"
                    )
                    currentWebView
                },
                modifier = Modifier.fillMaxSize(),
                onRelease = { webviewInstance ->
                    // Composable이 Composition에서 제거될 때 호출됩니다.
                    // 여기서 WebView 리소스를 정리합니다.
                    Log.d(
                        "WebViewScreen",
                        "AndroidView onRelease called for WebView: $webviewInstance, destroying."
                    )
                    webviewInstance.destroy()
                    webView = null // 상태에서도 제거
                }
                // update 블록은 Composable이 recompose 될 때마다 호출됩니다.
                // 만약 URL이 동적으로 변경되어야 한다면 여기서 처리할 수 있습니다.
                // update = { webviewInstance ->
                //    Log.d("WebViewScreen", "AndroidView update called for WebView: $webviewInstance")
                //    // 필요시 웹뷰 업데이트 로직 (예: URL 변경 시 webviewInstance.loadUrl(newUrl))
                //    if (webviewInstance.originalUrl != urlToLoad) { // URL이 변경된 경우에만 로드
                //        webviewInstance.loadUrl(urlToLoad)
                //    }
                // }
            )

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}