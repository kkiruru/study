package com.kkiruru.kmp.hello.intro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kkiruru.kmp.hello.LColor
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun IntroScreen(
    onNext: () -> Unit,
    scope: CoroutineScope = rememberCoroutineScope(),
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(Unit) {
        Napier.e(">>>>>> onNext.invoke()")
        scope.launch {
            delay(1000)
            Napier.e(">>>>>> onNext.invoke()")
            onNext.invoke()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(LColor.Green),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "IntroScreen"
        )
    }
}
