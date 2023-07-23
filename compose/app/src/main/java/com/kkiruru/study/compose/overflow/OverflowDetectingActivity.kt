package com.kkiruru.study.compose.overflow

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
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
            "보풀제거", "얼룩제거", "다림질", "얼룩제거", "얼룩제거", "원단복원", "원단복원", "원단복원"
        )

        var chipCount by remember { mutableStateOf(0) }

        Row(
            modifier = Modifier
                .padding(start = 40.dp, end = 24.dp)
                .wrapContentHeight()
                .background(color = Color.Blue),
            verticalAlignment = Alignment.CenterVertically
        ) {

//            Spacer(modifier = Modifier.width(1.5.dp))

            ChipOverflow(
                modifier = Modifier
                    .weight(1f, fill = false),
                onPlacementComplete = { chipCount = it },
                content = {
                    for (state in states) {
                        Log.e("tab", "state ${state}")
                        Chip(
                            modifier = Modifier
                                .padding(2.dp)
                                .wrapContentSize(),
                            text = state
                        )
                    }
                },
                overflowContent = {
                    for (i in states.indices) {
                        Log.e("tab", "외${i}개")
                        Text(
                            text = "외${i}개",
                            style = MaterialTheme.typography.h6
                        )
                    }
                }
            )
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
fun ChipOverflow(
    modifier: Modifier = Modifier,
    onPlacementComplete: (Int) -> Unit,
    content: @Composable () -> Unit,
    overflowContent: @Composable () -> Unit,
) {

    Layout(
        modifier = modifier,
        content = overflowContent
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints) }
        val items = mutableListOf<Item>()

        Log.e("tag","overflowContent constraints.maxWidth ${constraints.maxWidth}, items ${placeables.size}")

        for (placeable in placeables) {
            Log.e("tag","overflowContent,  ${placeable.width}")
            items.add(Item(placeable, 0))
        }
        layout(
            width = 0,
            height = items.maxOf { it.placeable.height }
        ) {
        }
    }

    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints) }
        val items = mutableListOf<Item>()
        var xPosition = 0

        Log.e("tag","constraints.maxWidth ${constraints.maxWidth}, items number ${placeables.size}")

        for (placeable in placeables) {
            if (xPosition + placeable.width > constraints.maxWidth) break
            items.add(Item(placeable, xPosition))
            xPosition += placeable.width
        }


        layout(
            width = items.last().let {
                Log.e("tag","_ it.xPosition ${it.xPosition},  it.placeable.width ${it.placeable.width}")
                it.xPosition + it.placeable.width
            },
            height = items.maxOf { it.placeable.height }
        ) {
            items.forEach {
                it.placeable.place(it.xPosition, 0)
            }
            onPlacementComplete(items.count())
        }
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
