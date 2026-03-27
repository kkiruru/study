package com.kkiruru.kmp.hello.main

// 웹뷰에서 호출할 수 있는 네이티브 함수들
interface WebViewBridge {
    fun postMessage(message: String)
}

// 웹뷰에 JavaScript를 주입하기 위한 인터페이스
expect fun setupWebViewBridge(webView: Any, bridge: WebViewBridge)