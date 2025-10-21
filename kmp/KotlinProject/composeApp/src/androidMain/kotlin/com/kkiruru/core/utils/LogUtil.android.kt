package com.kkiruru.kmp.hello.core.utils

import android.util.Log

actual fun logVerbose(tag: String, message: String) {
    Log.v(tag, message)
}

actual fun logDebug(tag: String, message: String) {
    Log.d(tag, message)
}

actual fun logInfo(tag: String, message: String) {
    Log.i(tag, message)
}

actual fun logWarning(tag: String, message: String) {
    Log.w(tag, message)
}

actual fun logError(tag: String, message: String, throwable: Throwable?) {
    if (throwable != null) {
        Log.e(tag, message, throwable)
    } else {
        Log.e(tag, message)
    }
}