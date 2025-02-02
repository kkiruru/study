package com.kkiruru.kmp.hello

import androidx.compose.ui.graphics.Color
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val color: Colors = IOSColors()
}

actual fun getPlatform(): Platform = IOSPlatform()


class IOSColors(
    override val Green: Color = Color(0xFF0AC290)
) : Colors
