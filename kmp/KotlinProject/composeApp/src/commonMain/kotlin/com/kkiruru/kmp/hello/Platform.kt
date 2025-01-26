package com.kkiruru.kmp.hello

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform