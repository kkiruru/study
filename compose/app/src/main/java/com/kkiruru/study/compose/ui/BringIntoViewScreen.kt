package com.kkiruru.study.compose.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun BringIntoViewRoute(

) {
    BringIntoViewScreen(
        inputOne = "1",
        inputOneChange = {},
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun BringIntoViewScreen(
    inputOne: String,
    inputOneChange: (String) -> Unit,
    scrollableState: ScrollState = rememberScrollState()
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollableState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        repeat(10) { PlaceholderCard() }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .bringIntoViewRequester(bringIntoViewRequester),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
//            elevation = 8.dp
        ) {
            Column {
                Text(modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp), text = "Please fill something in")
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .onFocusEvent { focusState ->
                            if (focusState.isFocused) {
                                coroutineScope.launch {
                                    bringIntoViewRequester.bringIntoView()
                                }
                            }
                        },
                    placeholder = { Text("Some input") },
                    value = inputOne,
                    singleLine = true,
                    onValueChange = inputOneChange,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, bottom = 16.dp, end = 16.dp),
                    onClick = { /*Nothing*/ }) {
                    Text("Click")
                }
            }
        }

        repeat(10) { PlaceholderCard() }
    }
}

@Composable
private fun PlaceholderCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
//        elevation = 8.dp
    ) {
        Text(modifier = Modifier.padding(32.dp), text = "Placeholder")
    }
}
