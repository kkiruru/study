package com.kkiruru.kmp.hello

import android.os.Build
import androidx.compose.ui.graphics.Color

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val color: Colors = AndroidColors()
}

actual fun getPlatform(): Platform = AndroidPlatform()

class AndroidColors(
    override val Green: Color = Color(0xFF51CA97)
) : Colors

