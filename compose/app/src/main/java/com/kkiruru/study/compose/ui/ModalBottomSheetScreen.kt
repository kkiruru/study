package com.kkiruru.study.compose.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ModalBottomSheetScreenRoute() {

    val viewModel = viewModel<ModalBottomSheetViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ModalBottomSheetViewModel() as T
            }
        }
    )

    ModalBottomSheetScreen(
        onEvent = viewModel::setEvent,
        viewModel = viewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ModalBottomSheetScreen(
    onEvent: (ModalBottomSheetEvent) -> Unit,
    viewModel : ModalBottomSheetViewModel,
) {
    val uiState = viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
        confirmValueChange = { false }
    )

    LaunchedEffect(uiState.value.bottomSheetUiState) {
        if (uiState.value.bottomSheetUiState != BottomSheetState.None) {
            if (!sheetState.isVisible && showBottomSheet) {
                onEvent(ModalBottomSheetEvent.OnDismissBottomSheetRequest)
                return@LaunchedEffect
            }
            showBottomSheet = true
        } else {

            Log.e("ModalBottomSheetScreen", "BottomSheetState.None")
            Log.e("ModalBottomSheetScreen", "__ sheetState ${sheetState.isVisible}")
            Log.e("ModalBottomSheetScreen", "__ showBottomSheet ${showBottomSheet}")

            scope.launch {
                Log.e("ModalBottomSheetScreen", "____ sheetState.hide()")
                sheetState.hide()
            }.invokeOnCompletion {
                Log.e("ModalBottomSheetScreen", "____ invokeOnCompletion")
                if (!sheetState.isVisible) {
                    Log.e("ModalBottomSheetScreen", ">>>> invokeOnCompletion: showBottomSheet = false")
                    showBottomSheet = false
                }
                showBottomSheet = false
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier,
                onClick = {
                    onEvent.invoke(ModalBottomSheetEvent.OnFirstClicked)
                }
            ) {
                Text("First bottom sheet")
            }

            Button(
                modifier = Modifier,
                onClick = {
                    onEvent.invoke(ModalBottomSheetEvent.OnSecondClicked("Hello"))
                }
            ) {
                Text("Second bottom sheet")
            }
        }


        Log.e("ModalBottomSheetScreen", "showBottomSheet ${showBottomSheet}")

        if (showBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier
                    .wrapContentHeight()
                    .heightIn(min = 150.dp),
                sheetState = sheetState,
                onDismissRequest = {
                    Log.e("ModalBottomSheetScreen", "onDismissRequest >> onEvent.invoke(OnDismissBottomSheetRequest)")
                    onEvent.invoke(ModalBottomSheetEvent.OnDismissBottomSheetRequest)
                }
            ) {
                BottomSheetContainer(
                    onEvent = onEvent,
                    scope = scope,
                    sheetState = sheetState,
                    uiState.value.bottomSheetUiState
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomSheetContainer(
    onEvent: (ModalBottomSheetEvent) -> Unit,
    scope: CoroutineScope,
    sheetState: SheetState,
    bottomSheet: BottomSheetState
) {

    when(bottomSheet) {
        BottomSheetState.FirstSheet -> {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                Text(
                    "FirstSheet",
                    modifier = Modifier.padding(16.dp)
                )

                Button(onClick = {
                    scope.launch {
                        onEvent.invoke(ModalBottomSheetEvent.OnDismissBottomSheetRequest)
                    }
                }) {
                    Text("Hide bottom sheet")
                }

                Button(onClick = {
//                    scope.launch {
//                        onEvent.invoke(ModalBottomSheetEvent.ShowSecondBottomSheetRequest)
//                    }

                    scope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        Log.e("ModalBottomSheetScreen", "____ invokeOnCompletion")
                        if (!sheetState.isVisible) {
                            Log.e("ModalBottomSheetScreen", ">>>> invokeOnCompletion: showBottomSheet = false")
                            onEvent.invoke(ModalBottomSheetEvent.ShowSecondBottomSheetRequest)
                        }
                    }
                }) {
                    Text("Second Sheet")
                }

            }
        }

        is BottomSheetState.SecondSheet -> {
            Column(
                modifier = Modifier
                    .heightIn(200.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "222222 ${bottomSheet.message}",
                    modifier = Modifier.padding(16.dp)
                )
                Button(onClick = {
                    scope.launch {
                        onEvent.invoke(ModalBottomSheetEvent.OnDismissBottomSheetRequest)
                    }
                }) {
                    Text("mission completed")
                }
            }
        }

        BottomSheetState.None -> {

        }
    }
}