package com.kkiruru.kmp.hello.core.utils

object LogUtil {
    private const val TAG = "KMP_App"

    fun v(message: String, tag: String = TAG) {
        logVerbose(tag, message)
    }

    fun d(message: String, tag: String = TAG) {
        logDebug(tag, message)
    }

    fun i(message: String, tag: String = TAG) {
        logInfo(tag, message)
    }

    fun w(message: String, tag: String = TAG) {
        logWarning(tag, message)
    }

    fun e(message: String, throwable: Throwable? = null, tag: String = TAG) {
        logError(tag, message, throwable)
    }
}

// 플랫폼별 구현을 위한 expect 함수들
expect fun logVerbose(tag: String, message: String)
expect fun logDebug(tag: String, message: String)
expect fun logInfo(tag: String, message: String)
expect fun logWarning(tag: String, message: String)
expect fun logError(tag: String, message: String, throwable: Throwable?)