package com.kkiruru.study.compose.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ModalBottomSheetViewModel2 : ViewModel() {
    private val _bottomSheetUiState = MutableStateFlow(BottomSheetUiState())
    val bottomSheetUiState = _bottomSheetUiState.asStateFlow()


    fun onEvent(event: ModalBottomSheet2Event) {
        Log.e("ModalBottomSheetViewModel2", "onEvent ${event}")
        when(event) {
            ModalBottomSheet2Event.DoShowFirstSheet -> {
                showBottomSheet(BottomSheetType.FirstSheet)
            }
            ModalBottomSheet2Event.OnHideBottomSheet -> {
                _bottomSheetUiState.update {
                    it.copy(
                        bottomSheetType = BottomSheetType.None
                    )
                }
            }

            is ModalBottomSheet2Event.DoShowSecondSheet -> {
                showBottomSheet(BottomSheetType.SecondSheet(event.message))

            }
        }
    }

    fun showBottomSheet(sheetType: BottomSheetType) {
        if (bottomSheetUiState.value.bottomSheetType == BottomSheetType.None) {
            _bottomSheetUiState.update {
                it.copy(
                    bottomSheetType = sheetType
                )
            }
        }
    }


    fun onSheetEvent(event: BottomSheetEvent) {
        when(event) {
            BottomSheetEvent.OnFirstSheet -> {
                onEvent(ModalBottomSheet2Event.OnHideBottomSheet)
            }

            is BottomSheetEvent.OnSecondClicked -> {
                onEvent(ModalBottomSheet2Event.OnHideBottomSheet)
            }
        }
    }
}


sealed interface ModalBottomSheet2Event{
    data object DoShowFirstSheet: ModalBottomSheet2Event
    data class DoShowSecondSheet(val message: String): ModalBottomSheet2Event
    data object OnHideBottomSheet: ModalBottomSheet2Event
}



data class BottomSheetUiState(
    val bottomSheetType: BottomSheetType = BottomSheetType.None
)


sealed interface BottomSheetType {
    data object None : BottomSheetType
    data object FirstSheet: BottomSheetType
    data class SecondSheet(val message: String): BottomSheetType
}

sealed interface BottomSheetEvent {
    data object OnFirstSheet : BottomSheetEvent
    data class OnSecondClicked(val message: String): BottomSheetEvent
}