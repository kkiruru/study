package com.kkiruru.kmp.hello

import androidx.compose.ui.graphics.Color

interface Platform {
    val name: String
    val color: Colors
}

expect fun getPlatform(): Platform


interface Colors{
    val Green: Color
}