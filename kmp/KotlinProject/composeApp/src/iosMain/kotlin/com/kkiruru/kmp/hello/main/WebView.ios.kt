package com.kkiruru.kmp.hello.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import com.kkiruru.kmp.hello.core.utils.LogUtil
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.WebKit.*
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun WebView(modifier: Modifier, url: String, onMessage: (String) -> Unit) {
    val bridge = remember {
        object : WebViewBridge {
            override fun postMessage(message: String) {
                LogUtil.d("postMessage: ${message}")
                onMessage(message)
            }
        }
    }

    val webView = remember {
        val config = WKWebViewConfiguration()
        val userContentController = WKUserContentController()

        // JavaScript 핸들러 등록
        val messageHandler = object : NSObject(), WKScriptMessageHandlerProtocol {
            override fun userContentController(
                userContentController: WKUserContentController,
                didReceiveScriptMessage: WKScriptMessage
            ) {
                val name = didReceiveScriptMessage.name
                val body = didReceiveScriptMessage.body.toString()

                LogUtil.d("iOS WebView 메시지 수신 - name: $name, body: $body")

                when (name) {
                    "postMessage" -> bridge.postMessage(body)
                }
            }
        }

        // postMessage 핸들러 등록
        userContentController.addScriptMessageHandler(messageHandler, "postMessage")

        // FlutterBridge JavaScript 객체 생성 (Android와 일관성 유지)
        val bridgeScript = WKUserScript(
            source = """
                window.FlutterBridge = {
                    postMessage: function(message) {
                        window.webkit.messageHandlers.postMessage.postMessage(message);
                    }
                };
                console.log('iOS FlutterBridge가 준비되었습니다.');
            """.trimIndent(),
            injectionTime = WKUserScriptInjectionTime.WKUserScriptInjectionTimeAtDocumentEnd,
            forMainFrameOnly = true
        )
        userContentController.addUserScript(bridgeScript)

        config.userContentController = userContentController
        config.preferences.javaScriptEnabled = true
        config.preferences.javaScriptCanOpenWindowsAutomatically = true

        WKWebView(frame = CGRectMake(0.0, 0.0, 0.0, 0.0), configuration = config)
    }

    UIKitView(
        factory = {
            webView.apply {
                allowsBackForwardNavigationGestures = true
            }
        },
        modifier = modifier,
        update = {
            NSURL.URLWithString(url)?.let { nsUrl ->
                val request = NSURLRequest.requestWithURL(nsUrl)
                webView.loadRequest(request)
            }
        }
    )
}

actual fun setupWebViewBridge(webView: Any, bridge: WebViewBridge) {
    // iOS에서는 WKWebView 생성 시 설정되므로 별도 구현 불필요
}