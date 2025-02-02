package com.kkiruru.kmp.hello

import androidx.compose.ui.graphics.Color

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
    override val color: Colors = JVMColors()

}

actual fun getPlatform(): Platform = JVMPlatform()

class JVMColors(
    override val Green: Color = Color(0xFF0AC290)
) : Colors
