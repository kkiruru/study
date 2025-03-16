package com.kkiruru.study.compose.ui.coordinator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CoordinatorRoute(

) {
    CoordinatorScreen(

    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CoordinatorScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("My App")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Handle click */ }
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add Button"
                )
            }
        }
    ) { contentPadding ->
        val pagerState = rememberPagerState {
            10
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(contentPadding)
        ) { /* Page contents */ }
    }
}