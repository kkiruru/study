package com.kkiruru.study.compose.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
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
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.material.swipeable
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SwipeableActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                SwipeableSheetApp()
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SwipeableSheetApp(
    modifier: Modifier = Modifier,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)
        var title by remember { mutableStateOf("") }
        val titleValue by rememberUpdatedState(newValue = title)


        var collapsed by remember { mutableStateOf(true) }
        val collapsedValue by rememberUpdatedState(newValue = collapsed)

        Text(
            text = "타이틀: $titleValue",
            style = MaterialTheme.typography.titleLarge ,
            color = Color(0xFF212121),
            fontWeight = FontWeight.W600
        )

        CollectionDetailScreen(
            modifier = Modifier.weight(1f),
            collapsed = collapsedValue,
        )

        Row (
            modifier = Modifier
                .height(56.dp)
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
                    style = MaterialTheme.typography.titleMedium ,
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
                    style = MaterialTheme.typography.titleMedium ,
                    color = Color(0xFF212121),
                    fontWeight = FontWeight.W600
                )
            }

       }
    }
}

@Composable
private fun CollectionDetailScreen(
    modifier: Modifier = Modifier,
    collapsed: Boolean = true,
) {
    FlexibleBottomSheet(
        modifier = modifier,
        backLayerContent = { BackLayer() },
        frontLayerContent = { FrontLayer() },
        collapsed = collapsed,
        onCollapse = {},
    )
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
                Column (
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
                Column (
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

    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        listState.interactionSource.interactions.collect {
            //at the top of the list so allow sheet scrolling
//            listener.allowSheetDrag(listState.firstVisibleItemScrollOffset == 0)/**/
            Log.e("tag", "listener.allowSheetDrag(${listState.firstVisibleItemScrollOffset == 0})")
        }
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                Log.e("tag", "NestedScrollConnection onPreScroll($available: Offset, $source: NestedScrollSource)")
                return super.onPreScroll(available, source)
            }

            override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                Log.e("tag",  "NestedScrollConnection onPostScroll($consumed: Offset, $available: Offset, $source: NestedScrollSource)")
                if (available.y > 0.0 && consumed.y == 0.0f) {
                    //scolling down up but we're already at the top - kick over to sheet scrolling
//                    listener.allowSheetDrag(true)

                    Log.e("tag", "listener.allowSheetDrag(true)")
                }
                return super.onPostScroll(consumed, available, source)
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .nestedScroll(nestedScrollConnection)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "맡길 세탁물",
            color = Color(0xFF212121),
            fontWeight = FontWeight.W600
        )

        Spacer(modifier = Modifier.height(30.dp))

        LazyColumn(
            state = lazyListState
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

@Preview(showBackground = true)
@Composable
private fun DefaultBackdropBottomSheet(
    modifier: Modifier = Modifier
) {
    MaterialTheme {
        FlexibleSheetApp()
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun FlexibleBottomSheet(
    modifier: Modifier,
    backLayerContent: @Composable () -> Unit,
    frontLayerContent: @Composable () -> Unit,
    headerHeight: Dp = 48.dp,
    collapsed: Boolean,
    onCollapse: (Boolean) -> Unit,
    ) {
    val localDensity = LocalDensity.current

    var collapse by remember { mutableStateOf(collapsed) }

    var backdropLayerHeightPx by remember { mutableStateOf(0f) }
    var backdropLayerHeightDp by remember { mutableStateOf(0.dp) }

    Box(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()
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

        var backLayerHeightDp by remember {
            mutableStateOf(0.dp)
        }

        var frontLayerHeightPx by remember {
            mutableStateOf(0f)
        }

        var frontLayerHeightDp by remember {
            mutableStateOf(0.dp)
        }

        Column(
            modifier = Modifier
                .wrapContentHeight()
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

        val frontHeightDp = backdropLayerHeightDp - backLayerHeightDp
        var currentHeight by remember { mutableStateOf(frontHeightDp) }

        val swipeableState = androidx.compose.material.rememberSwipeableState(
            initialValue = 0,
        )

        val point = LocalDensity.current.run { backLayerHeightDp.toPx()}

        val anchors = mapOf(0f to 0, -point to 1)

        if (swipeableState.isAnimationRunning) {
            DisposableEffect(Unit) {
                onDispose {
                    collapse = swipeableState.currentValue != 1
                    onCollapse(collapse)
                }
            }
        }

        currentHeight = frontHeightDp - with(localDensity) { swipeableState.offset.value.toDp() }

        Log.e("tag", " ${frontHeightDp} - ${with(localDensity) { swipeableState.offset.value.toDp()}} => ${currentHeight}")
        Log.e("tag", " point : ${point}, ${swipeableState.currentValue}")

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
                .swipeable(
                    state = swipeableState,
                    orientation = Orientation.Vertical,
                    anchors = anchors,
                    thresholds = { _, _ -> FractionalThreshold(0.5f) },
                    velocityThreshold = 1000.dp
                )
                .nestedScroll(
                    connection = object : NestedScrollConnection {
//                        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
//                            Log.e("tag", "onPreScroll available ${available}, ${source.toString()}")
//                            return Offset.Zero
//                        }

                        override fun onPostScroll(
                            consumed: Offset,
                            available: Offset,
                            source: NestedScrollSource
                        ): Offset {
                            Log.e("tag", "onPostScroll consumed ${consumed}, available ${available}  ${source.toString()}")
                            return Offset.Zero
                        }
                    }
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
