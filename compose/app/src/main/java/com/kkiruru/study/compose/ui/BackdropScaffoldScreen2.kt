package com.kkiruru.study.compose.ui

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffoldDefaults
import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.collapse
import androidx.compose.ui.semantics.expand
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import com.kkiruru.study.compose.ui.theme.LColor
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

@Composable
fun BackdropScaffoldScreen2Route() {
    BackdropScaffoldScreen2()
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)
@Composable
private fun BackdropScaffoldScreen2(
) {

    val scope = rememberCoroutineScope()
    val selection = remember { mutableStateOf(1) }
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)

    LaunchedEffect(scaffoldState) {
        scaffoldState.reveal()
    }
    BackdropScaffold2(
        scaffoldState = scaffoldState,
        appBar = {
            TopAppBar(
                title = { Text("Backdrop scaffold") },
                navigationIcon = {
                    if (scaffoldState.isConcealed) {
                        IconButton(onClick = { scope.launch { scaffoldState.reveal() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Localized description")
                        }
                    } else {
                        IconButton(onClick = { scope.launch { scaffoldState.conceal() } }) {
                            Icon(Icons.Default.Close, contentDescription = "Localized description")
                        }
                    }
                },
                actions = {
                    var clickCount by remember { mutableStateOf(0) }
                    IconButton(
                        onClick = {
                            // show snackbar as a suspend function
                            scope.launch {
                                scaffoldState.snackbarHostState
                                    .showSnackbar("Snackbar #${++clickCount}")
                            }
                        }
                    ) {
                        Icon(Icons.Default.Favorite, contentDescription = "Localized description")
                    }
                },
                elevation = 0.dp,
                backgroundColor = Color.Transparent
            )
        },
        backLayerContent = {
            LazyColumn(
                modifier = Modifier.fillMaxSize().background(color = Color.White)
            ) {
                items(if (selection.value >= 3) 3 else 5) {
                    ListItem(
                        Modifier.clickable {
                            selection.value = it
                            scope.launch { scaffoldState.conceal() }
                        },
                        text = {
                            Text("Select $it", color = Color.Black)
                        }
                    )
                }
            }
        },
        frontLayerShape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp),
        frontLayerBackgroundColor = Color.Transparent,
        frontLayerElevation = 0.dp,
        frontLayerScrimColor = Color.Unspecified,
        frontLayerContent = {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .background(color = LColor.Gray400)
            ) {
                item {
                    Spacer(modifier = Modifier.height(30.dp))
                    Text("Selection: ${selection.value}")
                }
                items(50) {
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
    )
}


@Composable
@ExperimentalMaterialApi
private fun BackdropScaffold2(
    appBar: @Composable () -> Unit,
    backLayerContent: @Composable () -> Unit,
    frontLayerContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    scaffoldState: BackdropScaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed),
    gesturesEnabled: Boolean = true,
    peekHeight: Dp = BackdropScaffoldDefaults.PeekHeight,
    headerHeight: Dp = BackdropScaffoldDefaults.HeaderHeight,
    persistentAppBar: Boolean = true,
    stickyFrontLayer: Boolean = true,
    backLayerBackgroundColor: Color = MaterialTheme.colors.primary,
    backLayerContentColor: Color = contentColorFor(backLayerBackgroundColor),
    frontLayerShape: Shape = BackdropScaffoldDefaults.frontLayerShape,
    frontLayerElevation: Dp = BackdropScaffoldDefaults.FrontLayerElevation,
    frontLayerBackgroundColor: Color = MaterialTheme.colors.surface,
    frontLayerContentColor: Color = contentColorFor(frontLayerBackgroundColor),
    frontLayerScrimColor: Color = BackdropScaffoldDefaults.frontLayerScrimColor,
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) }
) {
    val peekHeightPx = with(LocalDensity.current) { peekHeight.toPx() }
    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }

    val backLayer = @Composable {
        if (persistentAppBar) {
            Column {
                appBar()
                backLayerContent()
            }
        } else {
//            BackLayerTransition(scaffoldState.targetValue, appBar, backLayerContent)
        }
    }
    val calculateBackLayerConstraints: (Constraints) -> Constraints = {
        it.copy(minWidth = 0, minHeight = 0).offset(vertical = -headerHeightPx.roundToInt())
    }

    val localDensity = LocalDensity.current

    // Back layer
    Surface(
        color = backLayerBackgroundColor,
        contentColor = backLayerContentColor
    ) {
        val scope = rememberCoroutineScope()
        BackdropStack2(
            modifier.fillMaxSize(),
            backLayer,
            calculateBackLayerConstraints
        ) { constraints, backLayerHeight ->
            val fullHeight = constraints.maxHeight.toFloat()
            var revealedHeight = fullHeight - headerHeightPx
            if (stickyFrontLayer) {
                revealedHeight = min(revealedHeight, backLayerHeight)
            }
            val nestedScroll = if (gesturesEnabled) {
                Modifier //.nestedScroll(scaffoldState.nestedScrollConnection)
            } else {
                Modifier
            }

            val swipeable = Modifier
                .then(nestedScroll)
//                .anchoredDraggable(
//                    state = mapOf(
//                        peekHeightPx to BackdropValue.Concealed,
//                        revealedHeight to BackdropValue.Revealed
//                    ),
//                    orientation = Orientation.Vertical,
//                )
//                .swipeable(
//                    state = scaffoldState,
//                    anchors = mapOf(
//                        peekHeightPx to BackdropValue.Concealed,
//                        revealedHeight to BackdropValue.Revealed
//                    ),
//                    orientation = Orientation.Vertical,
//                    enabled = gesturesEnabled
//                )
                .semantics {
                    if (scaffoldState.isConcealed) {
                        collapse {
//                            if (scaffoldState.confirmStateChange(BackdropValue.Revealed)) {
//                                scope.launch { scaffoldState.reveal() }
//                            }; true
                            scope.launch { scaffoldState.reveal() }
                            true
                        }
                    } else {
                        expand {
//                            if (scaffoldState.confirmStateChange(BackdropValue.Concealed)) {
//                                scope.launch { scaffoldState.conceal() }
//                            }; true
                            scope.launch { scaffoldState.conceal() }
                            true
                        }
                    }
                }
            val frontLayerHeight = revealedHeight.dp + with(localDensity) { 10.dp }

            // Front layer
//            Surface(
//                Modifier
//                    .height(frontLayerHeight)
////                    .offset { IntOffset(0, scaffoldState.offset.value.roundToInt()) }
//                    .then(swipeable),
//                shape = frontLayerShape,
//                elevation = frontLayerElevation,
//                color = frontLayerBackgroundColor,
//                contentColor = frontLayerContentColor
//            ) {
            Box(
                Modifier
                    .padding(bottom = peekHeight)
                    .then(swipeable)
                    .height(frontLayerHeight)
            ) {
                frontLayerContent()
//                    Scrim(
//                        color = frontLayerScrimColor,
//                        onDismiss = {
////                            if (gesturesEnabled && scaffoldState.confirmStateChange(BackdropValue.Concealed)) {
////                                scope.launch { scaffoldState.conceal() }
////                            }
//                            scope.launch { scaffoldState.conceal() }
//                        },
//                        visible = scaffoldState.targetValue == BackdropValue.Revealed
//                    )
            }
//            }

            // Snackbar host
//            Box(
//                Modifier
//                    .padding(
//                        bottom = if (scaffoldState.isRevealed &&
//                            revealedHeight == fullHeight - headerHeightPx
//                        ) headerHeight else 0.dp
//                    ),
//                contentAlignment = Alignment.BottomCenter
//            ) {
//                snackbarHost(scaffoldState.snackbarHostState)
//            }
        }
    }
}

private enum class BackdropLayers2 { Back, Front }

@Composable
@UiComposable
private fun BackdropStack2(
    modifier: Modifier,
    backLayer: @Composable @UiComposable () -> Unit,
    calculateBackLayerConstraints: (Constraints) -> Constraints,
    frontLayer: @Composable @UiComposable (Constraints, Float) -> Unit
) {
    SubcomposeLayout(modifier) { constraints ->
        val backLayerPlaceable =
            subcompose(BackdropLayers2.Back, backLayer).first()
                .measure(calculateBackLayerConstraints(constraints))

        val backLayerHeight = backLayerPlaceable.height.toFloat()

//        val placeables =
//            subcompose(BackdropLayers2.Front) {
//                frontLayer(constraints, backLayerHeight)
//            }.fastMap { it.measure(constraints) }

        var maxWidth = max(constraints.minWidth, backLayerPlaceable.width)
        var maxHeight = max(constraints.minHeight, backLayerPlaceable.height)
//        placeables.fastForEach {
//            maxWidth = max(maxWidth, it.width)
//            maxHeight = max(maxHeight, it.height)
//        }

        layout(maxWidth, maxHeight) {
            backLayerPlaceable.placeRelative(0, 0)
//            placeables.fastForEach { it.placeRelative(0, 0) }
        }
    }
}


@Composable
private fun Scrim(
    color: Color,
    onDismiss: () -> Unit,
    visible: Boolean
) {
    if (color.isSpecified) {
        val alpha by animateFloatAsState(
            targetValue = if (visible) 1f else 0f,
            animationSpec = TweenSpec()
        )
        val dismissModifier = if (visible) {
            Modifier.pointerInput(Unit) { detectTapGestures { onDismiss() } }
        } else {
            Modifier
        }
        Canvas(
            Modifier
                .fillMaxSize()
                .then(dismissModifier)
        ) {
            drawRect(color = color, alpha = alpha)
        }
    }
}
