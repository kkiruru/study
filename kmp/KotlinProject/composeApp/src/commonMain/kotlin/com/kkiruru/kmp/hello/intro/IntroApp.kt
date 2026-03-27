package com.kkiruru.kmp.hello.intro

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun IntroScreenRoute(
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IntroScreen(
        onNext = onNext
    )
}
