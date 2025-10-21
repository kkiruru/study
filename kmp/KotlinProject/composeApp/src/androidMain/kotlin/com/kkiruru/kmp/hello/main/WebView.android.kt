package com.kkiruru.kmp.hello.main

import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.kkiruru.kmp.hello.core.utils.LogUtil

@Composable
actual fun WebView(modifier: Modifier, url: String, onMessage: (String) -> Unit) {
    val context = LocalContext.current

    val bridge = object : WebViewBridge {
        override fun postMessage(message: String) {
            LogUtil.d( "postMessage ${message}")
            onMessage(message)
        }
    }


    AndroidView(
        factory = { ctx ->
            WebView(ctx).apply {
                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    databaseEnabled = true
                    builtInZoomControls = true
                    displayZoomControls = false
                    setSupportZoom(true)
                    loadWithOverviewMode = true
                    useWideViewPort = true
                    cacheMode = android.webkit.WebSettings.LOAD_DEFAULT
                    mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                }


                LogUtil.d("addJavascriptInterface 시작:: $url")
                // JavaScript Interface 추가
                addJavascriptInterface(AndroidBridge(bridge), "FlutterBridge")

                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        LogUtil.d("페이지 로딩 시작:: $url")
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        LogUtil.d("페이지 로딩 완료:: $url")

                        view?.evaluateJavascript("""
                        (function() {
                            console.log('Android FlutterBridge 초기화 중...');
                            
                            if (typeof FlutterBridge !== 'undefined') {
                                console.log('✅ FlutterBridge 사용 가능');
                            } else {
                                console.error('❌ FlutterBridge를 찾을 수 없습니다.');
                            }
                        })();
                    """.trimIndent(), null)
                    }

                    override fun onReceivedError(
                        view: WebView?,
                        errorCode: Int,
                        description: String?,
                        failingUrl: String?
                    ) {
                        super.onReceivedError(view, errorCode, description, failingUrl)
                        LogUtil.e("WebView  에러 발생: $description (코드: $errorCode)", tag = "WebView")
                    }
                }

                loadUrl(url)
            }
        },
        modifier = modifier
    )
}

// JavaScript Interface 구현
class AndroidBridge(private val bridge: WebViewBridge) {
    @JavascriptInterface
    fun postMessage(message: String) {
        bridge.postMessage(message)
    }
}

actual fun setupWebViewBridge(webView: Any, bridge: WebViewBridge) {
    if (webView is WebView) {
        webView.addJavascriptInterface(AndroidBridge(bridge), "FlutterBridge")
    }
}