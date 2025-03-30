package com.kkiruru.study.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Text
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.kkiruru.study.compose.flexiblesheet.DraggableActivity
import com.kkiruru.study.compose.overflow.OverflowDetectingActivity
import com.kkiruru.study.compose.performance.PerformanceActivity
import com.kkiruru.study.compose.textfield.TextFieldActivity
import com.kkiruru.study.compose.viewpager.ViewPager2Activity
import com.kkiruru.study.compose.viewpager.ViewPagerActivity
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bring_into_view)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MaterialTheme {
                val state = rememberLazyListState()

                LazyColumn(
                    modifier = Modifier
                        .statusBarsPadding()
                        .fillMaxSize(),
                    state = state,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    item {
                        Button(onClick = {
                            startActivity(
                                Intent(this@MainActivity, BringIntoViewActivity::class.java)
                            )
                        }) {
                            Text(text = "bring Into View")
                        }

                        Button(onClick = {
                            startActivity(
                                Intent(this@MainActivity, NestedScrollActivity::class.java)
                            )
                        }) {
                            Text(text = "Nested Scroll")
                        }

                        Button(onClick = {
                            startActivity(
                                Intent(this@MainActivity, TransitionActivity::class.java)
                            )
                        }) {
                            Text(text = "transition")
                        }

                        Button(onClick = {
                            startActivity(
                                Intent(this@MainActivity, BackdropScaffoldActivity::class.java)
                            )
                        }) {
                            Text(text = "backDrop")
                        }

                        Button(onClick = {
                            startActivity(
                                Intent(this@MainActivity, BackdropScaffold2Activity::class.java)
                            )
                        }) {
                            Text(text = "backDrop2")
                        }

                        Button(onClick = {
                            startActivity(
                                Intent(this@MainActivity, ArgumentActivity::class.java)
                            )
                        }) {
                            Text(text = "argument")
                        }

                        Button(onClick = {
                            startActivity(
                                Intent(this@MainActivity, BottomSheetDialogActivity::class.java)
                            )
                        }) {
                            Text(
                                text = "BottomSheetDialog",
                                modifier = Modifier,
                                color = Color.White,
                            )
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(this@MainActivity, ModalBottomSheetActivity::class.java)
                            )
                        }) {
                            Text(
                                text = "ModalBottomSheet",
                                modifier = Modifier,
                                color = Color.White,
                            )
                        }

                        Button(onClick = {
                            startActivity(
                                Intent(this@MainActivity, ViewPagerActivity::class.java)
                            )
                        }) {
                            Text(text = "ViewPagerActivity")
                        }

                        Button(onClick = {
                            startActivity(
                                Intent(this@MainActivity, ViewPager2Activity::class.java)
                            )
                        }) {
                            Text(
                                text = "ViewPager2",
                                modifier = Modifier,
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }

                        Button(onClick = {
                            startActivity(
                                Intent(this@MainActivity, FlexibleSheetActivity::class.java)
                            )
                        }) {
                            Text(
                                text = "FlexibleSheet",
                                modifier = Modifier,
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }

                        Button(onClick = {
                            startActivity(
                                Intent(this@MainActivity, DraggableActivity::class.java)
                            )
                        }) {
                            Text(
                                text = "Draggable",
                                modifier = Modifier,
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }

                        Button(onClick = {
                            startActivity(
                                Intent(this@MainActivity, SwipeableActivity::class.java)
                            )
                        }) {
                            Text(
                                text = "Swipeable",
                                modifier = Modifier,
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }

                        Button(onClick = {
                            startActivity(
                                Intent(this@MainActivity, OverflowDetectingActivity::class.java)
                            )
                        }) {
                            Text(
                                text = "Overflow",
                                modifier = Modifier,
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }

                        Button(onClick = {
                            startActivity(
                                Intent(this@MainActivity, ConstraintActivity::class.java)
                            )
                        }) {
                            Text(
                                text = "Constraint",
                                modifier = Modifier,
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }

                        Button(onClick = {
                            startActivity(
                                Intent(this@MainActivity, FontStyleActivity::class.java)
                            )
                        }) {
                            Text(
                                text = "FontStyle",
                                modifier = Modifier,
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }

                        Button(onClick = {
                            startActivity(
                                Intent(this@MainActivity, TextFieldActivity::class.java)
                            )
                        }) {
                            Text(
                                text = "TextField",
                                modifier = Modifier,
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }

                        Button(onClick = {
                            startActivity(
                                Intent(this@MainActivity, PerformanceActivity::class.java)
                            )
                        }) {
                            Text(
                                text = "Performance",
                                modifier = Modifier,
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }

                        SwipeableSample()
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableSample() {
    val width = 96.dp
    val squareSize = 48.dp

    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states

    Box(
        modifier = Modifier
            .width(width)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
            .background(Color.LightGray)
    ) {
        Box(
            Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .size(squareSize)
                .background(Color.DarkGray)
        )
    }
}
