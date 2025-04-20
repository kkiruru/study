package com.kkiruru.study.compose.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
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
fun ModalBottomSheetScreenRoute2(
    onNavigateUp: () -> Unit,
) {

    val viewModel = viewModel<ModalBottomSheetViewModel2>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ModalBottomSheetViewModel2() as T
            }
        }
    )

    BackHandler(enabled = true) {
        onNavigateUp()
    }

    val bottomSheetUiState = viewModel.bottomSheetUiState.collectAsState()

    ModalBottomSheetScreen2(
        onEvent = viewModel::onEvent,
        onSheetEvent = viewModel::onSheetEvent,
        bottomSheetUiState = bottomSheetUiState.value,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ModalBottomSheetScreen2(
    onEvent: (ModalBottomSheet2Event) -> Unit,
    onSheetEvent: (BottomSheetEvent) -> Unit,
    bottomSheetUiState: BottomSheetUiState,
) {
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
        confirmValueChange = { false }
    )

    LaunchedEffect(bottomSheetUiState) {
        if (bottomSheetUiState.bottomSheetType != BottomSheetType.None) {
            if (!sheetState.isVisible && showBottomSheet) {
                onEvent(ModalBottomSheet2Event.OnHideBottomSheet)
                return@LaunchedEffect
            }
            showBottomSheet = true
        } else {
            scope.launch {
                sheetState.hide()
            }.invokeOnCompletion {
                if (!sheetState.isVisible) {
                    showBottomSheet = false
                }
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
                    onEvent.invoke(ModalBottomSheet2Event.DoShowFirstSheet)
                }
            ) {
                Text("First bottom sheet")
            }

            Button(
                modifier = Modifier,
                onClick = {
                    onEvent.invoke(ModalBottomSheet2Event.DoShowSecondSheet("Hello World"))
                }
            ) {
                Text("Second bottom sheet")
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier.fillMaxHeight(),
                sheetState = sheetState,
                onDismissRequest = {
                    onEvent(ModalBottomSheet2Event.OnHideBottomSheet)
                }
            ) {
                BottomSheetContainer(
                    onEvent = onSheetEvent,
                    scope = scope,
                    bottomSheet = bottomSheetUiState,
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomSheetContainer(
    onEvent: (BottomSheetEvent) -> Unit,
    scope: CoroutineScope,
    bottomSheet: BottomSheetUiState
) {

    when(bottomSheet.bottomSheetType) {
        BottomSheetType.FirstSheet -> {
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
                        onEvent.invoke(BottomSheetEvent.OnFirstSheet)
//                        sheetState.hide() }.invokeOnCompletion {
//                        if (!sheetState.isVisible) {
//
//                        }
                    }
                }) {
                    Text("Hide bottom sheet")
                }
            }
        }

        is BottomSheetType.SecondSheet -> {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                Text(text = "222222 ${bottomSheet.bottomSheetType.message}",
                    modifier = Modifier.padding(16.dp)
                )
                Button(onClick = {
                    scope.launch {
                        onEvent.invoke(BottomSheetEvent.OnSecondClicked(bottomSheet.bottomSheetType.message))
                    }
                }) {
                    Text("mission completed")
                }
            }
        }

        BottomSheetType.None -> { }
    }
}