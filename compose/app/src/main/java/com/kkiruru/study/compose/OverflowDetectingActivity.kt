package com.kkiruru.study.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class OverflowDetectingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                OverflowDetectingApp()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Column {

        val states = arrayOf(
            "NY", "CA", "NV", "PA", "AZ12312312311123", "AK12312123123213", "NE123123", "CT", "CO", "FL", "IL", "KS", "WA")

        var chipCount by remember { mutableStateOf(0) }

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ChipRow2(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .weight(1f, fill = false),
                onPlacementComplete = { chipCount = it }
            ) {
                for (state in states) {
                    Chip(
                        modifier = Modifier
                            .padding(4.dp)
                            .wrapContentSize(), text = state
                    )
                }
            }
            Log.e("tag", "${states.size - chipCount}")

            if (chipCount < states.size) {
                Log.e("tag", "${states.size - chipCount} ${states[chipCount]}")
                Text(text = "외${states.size - chipCount}개", style = MaterialTheme.typography.h6)
            }
        }
    }
}

@Composable
fun Chip(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.LightGray,
        shape = CircleShape,
        modifier = modifier
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(6.dp)
        )
    }
}

@Composable
fun ChipRow(
    modifier: Modifier = Modifier,
    onPlacementComplete: (Int) -> Unit,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->

        val placeables = measurables.map { it.measure(constraints) };

        var counter = 0

        layout(constraints.maxWidth, constraints.maxHeight) {
            var xPosition = 0
            for (placeable in placeables) {
                if (xPosition + placeable.width > constraints.maxWidth) break
                placeable.placeRelative(x = xPosition, 0)
                xPosition += placeable.width
                counter++
            }
            onPlacementComplete(counter)
        }
    }
}


data class Item(val placeable: Placeable, val xPosition: Int)

@Composable
fun ChipRow2(
    modifier: Modifier = Modifier,
    onPlacementComplete: (Int) -> Unit,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->

        val placeables = measurables.map { it.measure(constraints) };

//        var counter = 0

        val items = mutableListOf<Item>()
        var xPosition = 0
        for (placeable in placeables) {
            if (xPosition + placeable.width > constraints.maxWidth) break
            items.add(Item(placeable, xPosition))
            xPosition += placeable.width
        }

        layout(
            width = items.last().let { it.xPosition + it.placeable.width },
            height = items.maxOf { it.placeable.height }
        ) {
            items.forEach {
                it.placeable.place(it.xPosition, 0)
            }
            onPlacementComplete(items.count())
        }


//        layout(constraints.maxWidth, constraints.maxHeight) {
//            var xPosition = 0
//            for (placeable in placeables) {
//                if (xPosition + placeable.width > constraints.maxWidth) break
//                placeable.placeRelative(x = xPosition, 0)
//                xPosition += placeable.width
//                counter++
//            }
//            onPlacementComplete(counter)
//        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OverflowDetectingApp(
    modifier: Modifier = Modifier,
) {
    MainScreen()
}


@Preview(showBackground = true)
@Composable
private fun DefaultOverflowDetectingApp(
    modifier: Modifier = Modifier
) {
    MaterialTheme {
        OverflowDetectingApp()
    }
}
