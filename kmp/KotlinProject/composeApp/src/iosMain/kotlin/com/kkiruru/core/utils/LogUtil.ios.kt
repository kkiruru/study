package com.kkiruru.kmp.hello.core.utils

import platform.Foundation.NSLog

actual fun logVerbose(tag: String, message: String) {
    NSLog("[$tag] VERBOSE: $message")
}

actual fun logDebug(tag: String, message: String) {
    NSLog("[$tag] DEBUG: $message")
}

actual fun logInfo(tag: String, message: String) {
    NSLog("[$tag] INFO: $message")
}

actual fun logWarning(tag: String, message: String) {
    NSLog("[$tag] WARNING: $message")
}

actual fun logError(tag: String, message: String, throwable: Throwable?) {
    if (throwable != null) {
        NSLog("[$tag] ERROR: $message\nException: ${throwable.message}\nStackTrace: ${throwable.stackTraceToString()}")
    } else {
        NSLog("[$tag] ERROR: $message")
    }
}