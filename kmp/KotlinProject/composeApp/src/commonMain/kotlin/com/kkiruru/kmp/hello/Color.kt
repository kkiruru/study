package com.kkiruru.kmp.hello

import androidx.compose.ui.graphics.Color


class LColor {
    companion object {
        val Green: Color
            get() = getPlatform().color.Green
    }
}
