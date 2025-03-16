package com.kkiruru.study.compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class BackdropScaffoldActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val scope = rememberCoroutineScope()
            val selection = remember { mutableStateOf(1) }
            val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)
            LaunchedEffect(scaffoldState) {
                scaffoldState.reveal()
            }
            BackdropScaffold(
                scaffoldState = scaffoldState,
                appBar = {
                    TopAppBar(
                        title = { Text("Backdrop scaffold") },
                        navigationIcon = {
                            if (scaffoldState.isConcealed) {
                                IconButton(onClick = { scope.launch { scaffoldState.reveal() } }) {
                                    Icon(Icons.Default.Menu, contentDescription = "Localized description")
                                }
                            } else {
                                IconButton(onClick = { scope.launch { scaffoldState.conceal() } }) {
                                    Icon(Icons.Default.Close, contentDescription = "Localized description")
                                }
                            }
                        },
                        actions = {
                            var clickCount by remember { mutableStateOf(0) }
                            IconButton(
                                onClick = {
                                    // show snackbar as a suspend function
                                    scope.launch {
                                        scaffoldState.snackbarHostState
                                            .showSnackbar("Snackbar #${++clickCount}")
                                    }
                                }
                            ) {
                                Icon(Icons.Default.Favorite, contentDescription = "Localized description")
                            }
                        },
                        elevation = 0.dp,
                        backgroundColor = Color.Transparent
                    )
                },
                backLayerContent = {
                    LazyColumn {
                        items(if (selection.value >= 3) 3 else 5) {
                            ListItem(
                                Modifier.clickable {
                                    selection.value = it
                                    scope.launch { scaffoldState.conceal() }
                                },
                                text = { Text("Select $it") }
                            )
                        }
                    }
                },
                frontLayerShape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp),
                frontLayerBackgroundColor = Color.Transparent,
                frontLayerElevation = 0.dp,
                frontLayerScrimColor = Color.Unspecified,
                frontLayerContent = {
                    Column(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .background(color = Color.White)
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))
                        Text("Selection: ${selection.value}")
                        LazyColumn {
                            items(50) {
                                ListItem(
                                    text = { Text("Item $it") },
                                    icon = {
                                        Icon(
                                            Icons.Default.Favorite,
                                            contentDescription = "Localized description"
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}