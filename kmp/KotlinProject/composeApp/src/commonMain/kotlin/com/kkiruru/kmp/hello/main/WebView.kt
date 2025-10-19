package com.kkiruru.kmp.hello.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun WebView(modifier: Modifier = Modifier, url: String)
