package com.kkiruru.study.compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.kkiruru.study.compose.R
import kotlinx.coroutines.launch

class BringIntoViewActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bring_into_view)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MaterialTheme {
                MainContent(
                    inputOne = "1",
                    inputOneChange = {},
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyScreen() {
    Box {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize() // fill the entire window
                .imePadding() // padding for the bottom for the IME
                .imeNestedScroll(), // scroll IME at the bottom
            content = { }
        )
    }
}


@OptIn(ExperimentalFoundationApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun MainContent(
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
            elevation = 8.dp
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
        elevation = 8.dp
    ) {
        Text(modifier = Modifier.padding(32.dp), text = "Placeholder")
    }
}
