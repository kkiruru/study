package com.kkiruru.study.compose.performance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OneViewOneStateViewModel @Inject constructor() : BaseViewModel<MainState, MainAction>() {

    private var _state : MutableStateFlow<MainState> = MutableStateFlow(initialState())
    val state : StateFlow<MainState>
        get() = _state.asStateFlow()


    override fun processAction(action: MainAction) {
        when (action) {

            MainAction.ContinueData -> {

                viewModelScope.launch {
                    var counter = 0
                    while (true) {
                        counter = ++counter

//                        if (counter % 2 == 0) {
//                            setState(
//                                getValue().copy(
//                                    playerAText = "A-${counter}",
//                                    listA = getValue().listA + counter
//                                )
//                            )
//                        }
//
//                        if (counter % 3 == 0) {
//                            setState(
//                                getValue().copy(
//                                    playerBText = "B-${counter}",
//                                    listB = getValue().listB + counter
//                                )
//                            )
//                        }
//
//                        if (counter % 5 == 0) {
//                            setState(
//                                getValue().copy(
//                                    playerCText = "C-${counter}",
//                                    listC = getValue().listC + counter
//                                )
//                            )
//                        }
//
//                        setState(
//                            getValue().copy(
//                                text = counter.toString()
//                            )
//                        )

                        delay(1000)
                    }

                }

            }

        }
    }

    override fun initialState(): MainState =
        MainState("", "A-0", "B-0", "C-0", true, emptyList(), emptyList(), emptyList())
}

data class MainState(
    val text: String,
    val playerAText: String,
    val playerBText: String,
    val playerCText: String,
    val shouldVisible: Boolean,
    val listA: List<Int>,
    val listB: List<Int>,
    val listC: List<Int>
)

sealed interface MainAction {
    data object ContinueData : MainAction
}

abstract class BaseViewModel<S,A> : ViewModel() {
    abstract fun processAction(action: A)
    abstract fun initialState(): S
}
