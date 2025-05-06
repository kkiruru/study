package com.kkiruru.study.compose.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
fun BottomSheetScaffoldScreenRoute() {
    val viewModel = viewModel<ModalBottomSheetViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ModalBottomSheetViewModel() as T
            }
        }
    )

    BottomSheetScaffoldScreen(
        onEvent = viewModel::setEvent,
        viewModel = viewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomSheetScaffoldScreen(
    onEvent: (ModalBottomSheetEvent) -> Unit,
    viewModel : ModalBottomSheetViewModel,
) {

    val contextForToast = LocalContext.current.applicationContext
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        ),
    )

//    LaunchedEffect(scaffoldState.bottomSheetState) {
        Log.e(
            "BottomSheetScaffold",
            "scaffoldState.bottomSheetState " +
            "${scaffoldState.bottomSheetState.currentValue}" +
            "/${scaffoldState.bottomSheetState.targetValue}" +
            "/${scaffoldState.bottomSheetState.isVisible}" +
            "/${scaffoldState.bottomSheetState.hasExpandedState}" +
            "/${scaffoldState.bottomSheetState.hasPartiallyExpandedState}"
        )
//    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetShape = BottomSheetDefaults.ExpandedShape,
        sheetContent = {
            BottomSheetScreen()
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Rest of the app UI")

            Button(onClick = {
                coroutineScope.launch {
                    scaffoldState.bottomSheetState.expand()
                }
            }) {
                Text("Click to show sheet")
            }

            Button(onClick = {
                coroutineScope.launch {
                    scaffoldState.bottomSheetState.hide()
                }
            }) {
                Text("Click to hide sheet")
            }
        }
    }
}

@Composable
private fun BottomSheetScreen( ) {
    LazyColumn {
        // the first item that is visible
        item {
            Box(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
                    .background(color = Color.White)
            ) {
                Text(
                    text = "Swipe up to Expand the sheet",
                    modifier = Modifier.align(alignment = Alignment.Center),
                    color = Color.Black
                )
            }
        }

        item {
            Box(
                modifier = Modifier
                    .height(156.dp)
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primary)
            ) {
                LazyColumn {
                    items(count = 5) {

                    }
                }
            }
        }

        item {
            Box(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = "하단 UI",
                    modifier = Modifier.align(alignment = Alignment.Center),
                    color = Color.White
                )
            }
        }
    }
}



