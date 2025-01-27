package com.kkiruru.kmp.hello.intro

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController


@Composable
fun IntroScreenRoute(
    navHostController: NavHostController,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IntroScreen(
        onNext = onNext
    )
}
