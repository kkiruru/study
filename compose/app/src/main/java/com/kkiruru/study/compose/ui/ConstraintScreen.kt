package com.kkiruru.study.compose.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffoldDefaults
import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ConstraintSheetScreenRoute() {
    ConstraintSheetScreen(

    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ConstraintSheetScreen(
    modifier: Modifier = Modifier,
    scope: CoroutineScope = rememberCoroutineScope()
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFCCCCCC))
    ) {
        val (header, main, footer) = createRefs()


        val barrier = createTopBarrier(footer)

        val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)
        var title by remember { mutableStateOf("") }
        val titleValue by rememberUpdatedState(newValue = title)


        var collapsed by remember { mutableStateOf(false) }
        val collapsedValue by rememberUpdatedState(newValue = collapsed)

        Box(modifier = Modifier
            .constrainAs(header) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }) {
            Text(
                modifier = Modifier,
                text = "타이틀: $titleValue",
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF212121),
                fontWeight = FontWeight.W600
            )
        }

        BackdropBottomSheet(
            modifier = Modifier
                .constrainAs(main) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(header.bottom)
                    bottom.linkTo(barrier)
                    height = Dimension.fillToConstraints
                },
            backLayerContent = { BackLayer() },
            frontLayerContent = { FrontLayer() },
            scaffoldState = scaffoldState,
            collapsed = collapsed,
        )


        Box(modifier = Modifier
            .constrainAs(footer) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        ) {
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .background(color = Color.Gray)
            ) {
                Button(onClick = {
                    scope.launch {
                        scaffoldState.reveal()
                    }
                    title = "펼치기"
                    collapsed = false
                }
                ) {
                    Text(
                        text = "펼치기",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF212121),
                        fontWeight = FontWeight.W600
                    )
                }
                Button(onClick = {
                    scope.launch {
                        scaffoldState.conceal()
                    }
                    title = "접기"
                    collapsed = true
                }
                ) {
                    Text(
                        text = "접기",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF212121),
                        fontWeight = FontWeight.W600
                    )
                }

            }
        }
    }
}


@Composable
private fun BackLayer(
    modifier: Modifier = Modifier
) {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = Color(0xFFF7BCA7))
                .statusBarsPadding()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Surface(
                modifier = modifier
                    .fillMaxWidth()
                    .shadow(
                        shape = RoundedCornerShape(10.dp),
                        elevation = 7.dp,
                        ambientColor = Color(0x40939393),
                        spotColor = Color(0x40939393),
                    )
            ) {
                Column(
                    modifier = Modifier
                        .height(100.dp)
                        .padding(all = 15.dp),
                ) {
                    Text(
                        text = "개별클리닝",
                        color = Color(0xFF212121),
                        fontWeight = FontWeight.W600
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                modifier = modifier
                    .fillMaxWidth()
                    .shadow(
                        shape = RoundedCornerShape(10.dp),
                        elevation = 7.dp,
                        ambientColor = Color(0x40939393),
                        spotColor = Color(0x40939393),
                    )
            ) {
                Column(
                    modifier = Modifier
                        .height(84.dp)
                        .padding(all = 15.dp),
                ) {
                    Text(
                        text = "프리미엄",
                        color = Color(0xFF212121),
                        fontWeight = FontWeight.W600
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun FrontLayer(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFA35EF3))
            .padding(top = 20.dp, bottom = 5.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "맡길 세탁물",
            color = Color(0xFF212121),
            fontWeight = FontWeight.W600
        )

        Spacer(modifier = Modifier.height(30.dp))

        LazyColumn(
            state = lazyListState,
            modifier = Modifier.background(color = Color(0xFFA3F35E))
        ) {
            items(20) {
                ListItem(
                    text = { Text("Item $it") },
                    icon = {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Localized description"
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BackdropBottomSheet(
    modifier: Modifier,
    backLayerContent: @Composable () -> Unit,
    frontLayerContent: @Composable () -> Unit,
    scaffoldState: BackdropScaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed),
    headerHeight: Dp = BackdropScaffoldDefaults.HeaderHeight,
    collapsed: Boolean,

    ) {
    val localDensity = LocalDensity.current

    // Create element height in pixel state
    var backdropLayerHeightPx by remember {
        mutableStateOf(0f)
    }

    // Create element height in dp state
    var backdropLayerHeightDp by remember {
        mutableStateOf(0.dp)
    }

    Box(modifier = modifier
        .background(color = Color(0xFF68CFA4))
        .onGloballyPositioned { coordinates ->
            // Set column height using the LayoutCoordinates
            backdropLayerHeightPx = coordinates.size.height.toFloat()
            backdropLayerHeightDp = with(localDensity) { coordinates.size.height.toDp() }
        }
    ) {
        var backLayerHeightPx by remember {
            mutableStateOf(0f)
        }

        // Create element height in dp state
        var backLayerHeightDp by remember {
            mutableStateOf(0.dp)
        }

        var frontLayerHeightPx by remember {
            mutableStateOf(0f)
        }

        // Create element height in dp state
        var frontLayerHeightDp by remember {
            mutableStateOf(0.dp)
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .onGloballyPositioned { coordinates ->
                    // Set column height using the LayoutCoordinates
                    backLayerHeightPx = coordinates.size.height.toFloat()
                    backLayerHeightDp = with(localDensity) { coordinates.size.height.toDp() }
                }
        ) {
            backLayerContent()
        }

        var frontHeightDp by remember { mutableStateOf(300.dp) }
        val initHeightDp by remember { mutableStateOf(300.dp) }
        val COLLAPSED_HEIGHT = backdropLayerHeightDp - backLayerHeightDp
        val REVEALED_HEIGHT = backdropLayerHeightDp - headerHeight

        val targetHeight = if (collapsed) COLLAPSED_HEIGHT else REVEALED_HEIGHT
        var currentHeight by remember { mutableStateOf(initHeightDp) }

        frontHeightDp = if (collapsed) {
            backdropLayerHeightDp - backLayerHeightDp
        } else {
            backdropLayerHeightDp - headerHeight
        }

        Log.e("tag", ">>> scaffoldState ${scaffoldState.currentValue}")

        val draggableState = rememberDraggableState(
            onDelta = { delta ->
                currentHeight -= with(localDensity) { delta.toDp() }
            }
        )

        Box(
            modifier = Modifier
                .height(currentHeight)
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(color = Color(0xFF5EA3F3))
                .onGloballyPositioned { coordinates ->
                    // Set column height using the LayoutCoordinates
                    frontLayerHeightPx = coordinates.size.height.toFloat()
                    frontLayerHeightDp = with(localDensity) { coordinates.size.height.toDp() }
                }
                .draggable(
                    state = draggableState,
                    orientation = Orientation.Vertical,
                    onDragStarted = { Log.e("tag", "__ onDragStarted") },
                    onDragStopped = { Log.e("tag", "__ onDragStopped") },
                )
        ) {
            frontLayerContent()
        }

        // debug 정보
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(color = Color(0xFF444444))
        ) {
            Text(
                text = "backdropLayer : ${backdropLayerHeightPx}, ${backdropLayerHeightDp}",
                modifier = Modifier,
                color = Color.White,
            )
            Text(
                text = "backLayer : ${backLayerHeightPx}, ${backLayerHeightDp}",
                modifier = Modifier,
                color = Color.White,
            )
            Text(
                text = "front : ${frontLayerHeightPx}, ${frontLayerHeightDp}",
                modifier = Modifier,
                color = Color.White,
            )
        }
    }
}
