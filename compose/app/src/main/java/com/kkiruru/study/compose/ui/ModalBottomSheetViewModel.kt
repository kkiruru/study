package com.kkiruru.study.compose.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ModalBottomSheetViewModel : BaseViewModel<ModalBottomSheetEvent, ModalBottomSheetState, ModalBottomSheetEffect>() {


    override fun createInitialState(): ModalBottomSheetState {
        return ModalBottomSheetState(
            RandomNumberState.Idle
        )
    }

    override fun handleEvent(event: ModalBottomSheetEvent) {
        Log.e("", "handleEvent : ${event}")

        when(event) {
            ModalBottomSheetEvent.OnFirstClicked -> {
                viewModelScope.launch {
                    setState {
                        copy (
                            bottomSheetUiState = BottomSheetState.FirstSheet
                        )
                    }
                }
            }

            is ModalBottomSheetEvent.OnSecondClicked -> {
                viewModelScope.launch {
                    setState {
                        copy (
                            bottomSheetUiState = BottomSheetState.SecondSheet("Hello")
                        )
                    }
                }
            }

            ModalBottomSheetEvent.OnDismissBottomSheetRequest -> {
                hideBottomSheet()
            }
        }
    }

    private fun hideBottomSheet() {
        Log.e("ModalBottomSheetViewModel", "hideBottomSheet()")
        viewModelScope.launch {
            setState {
                copy (
                    bottomSheetUiState = BottomSheetState.None
                )
            }
        }
    }
}


sealed class ModalBottomSheetEvent : UiEvent {
    object OnFirstClicked : ModalBottomSheetEvent()
    data class OnSecondClicked(val message: String) : ModalBottomSheetEvent()
    data object OnDismissBottomSheetRequest: ModalBottomSheetEvent()
}

data class ModalBottomSheetState(
    val randomNumberState: RandomNumberState = RandomNumberState.Idle,
    val bottomSheetUiState: BottomSheetState = BottomSheetState.None,
) : UiState

sealed class RandomNumberState{
    object Idle : RandomNumberState()
    object Loading : RandomNumberState()
    data class Success(val number : Int) : RandomNumberState()
}

sealed class BottomSheetState{
    object FirstSheet : BottomSheetState()
    data class SecondSheet(val message: String) : BottomSheetState()
    object None : BottomSheetState()
}


sealed class ModalBottomSheetEffect : UiEffect {
    object None: ModalBottomSheetEffect()
    object ShowToast : ModalBottomSheetEffect()
    object HideBottomSheet: ModalBottomSheetEffect()
}
