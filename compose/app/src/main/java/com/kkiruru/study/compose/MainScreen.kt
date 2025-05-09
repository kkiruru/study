package com.kkiruru.study.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kkiruru.study.compose.ui.ArgumentScreenRoute
import com.kkiruru.study.compose.ui.BackdropScaffoldScreen2Route
import com.kkiruru.study.compose.ui.BackdropScaffoldScreenRoute
import com.kkiruru.study.compose.ui.BottomSheetDialogScreenRoute
import com.kkiruru.study.compose.ui.BottomSheetScaffoldScreenRoute
import com.kkiruru.study.compose.ui.BringIntoViewRoute
import com.kkiruru.study.compose.ui.ConstraintSheetScreenRoute
import com.kkiruru.study.compose.ui.FlexibleSheetScreenRoute
import com.kkiruru.study.compose.ui.FontStyleScreenRoute
import com.kkiruru.study.compose.ui.ModalBottomSheetScreenRoute
import com.kkiruru.study.compose.ui.NestedScrollRoute
import com.kkiruru.study.compose.ui.TransitionScreenRoute
import com.kkiruru.study.compose.ui.coordinator.CoordinatorRoute


enum class ExampleDestinations(val description: String = "") {
    MAIN_HOME,
    REGISTER_ADDRESS("CoordinatorLayout to Compose"),
    BRING_INTO_VIEW("BringIntoView"),
    NESTED_SCROLL("Nested Scroll"),
    TRANSITION("Transition"),
    BACK_DROP_SCAFFOLD("BackdropScaffold"),
    BACK_DROP_SCAFFOLD2("BackdropScaffold2"),
    ARGUMENT("Argument"),
    BOTTOM_SHEET_DIALOG("BottomSheetDialog"),
    CONSTRAINT_SHEET("CONSTRAINT_SHEET"),
    FLEXIBLE_SHEET("FlexibleSheet"),
    FONT_STYLE("FONT_STYLE"),
    MODAL_BOTTOM_SHEET("ModalBottomSheet"),
    BOTTOM_SHEET_SCAFFOLD3("BottomSheetScaffold 3"),
}

@Composable
fun MainNavHost(
    startDestination: String = ExampleDestinations.MAIN_HOME.toString(),
    navHostController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
    ) {
        composable(
            ExampleDestinations.MAIN_HOME.toString(),
        ){
            MainScreenRoute(
                onNavigation  = {
                    if (it == ExampleDestinations.MAIN_HOME.toString()) {
                        navHostController.navigate(it) {
                            popUpTo(navHostController.graph.id) {
                                inclusive = false
                            }
                        }
                    } else {
                        navHostController.navigate(it)
                    }
                },
                navHostController = navHostController,
            )
        }
        composable(
            ExampleDestinations.REGISTER_ADDRESS.toString(),
        ){
            CoordinatorRoute()
        }
        composable(
            ExampleDestinations.BRING_INTO_VIEW.toString(),
        ){
            BringIntoViewRoute()
        }
        composable(
            ExampleDestinations.NESTED_SCROLL.toString(),
        ){
            NestedScrollRoute()
        }
        composable(
            ExampleDestinations.TRANSITION.toString(),
        ){
            TransitionScreenRoute()
        }
        composable(
            ExampleDestinations.BACK_DROP_SCAFFOLD.toString(),
        ){
            BackdropScaffoldScreenRoute()
        }
        composable(
            ExampleDestinations.BACK_DROP_SCAFFOLD2.toString(),
        ){
            BackdropScaffoldScreen2Route()
        }
        composable(
            ExampleDestinations.ARGUMENT.toString(),
        ){
            ArgumentScreenRoute()
        }
        composable(
            ExampleDestinations.BOTTOM_SHEET_DIALOG.toString(),
        ){
            BottomSheetDialogScreenRoute()
        }
        composable(
            ExampleDestinations.CONSTRAINT_SHEET.toString(),
        ){
            ConstraintSheetScreenRoute()
        }
        composable(
            ExampleDestinations.FLEXIBLE_SHEET.toString(),
        ){
            FlexibleSheetScreenRoute()
        }
        composable(
            ExampleDestinations.FONT_STYLE.toString(),
        ){
            FontStyleScreenRoute()
        }
        composable(
            ExampleDestinations.MODAL_BOTTOM_SHEET.toString(),
        ){
            ModalBottomSheetScreenRoute()
        }
        composable(
            ExampleDestinations.BOTTOM_SHEET_SCAFFOLD3.toString(),
        ){
            BottomSheetScaffoldScreenRoute()
        }
    }
}



@Composable
fun MainScreenRoute(
    onNavigation: (String) -> Unit,
    navHostController: NavHostController
) {
    MainScreen(
        onNavigation = onNavigation,
    )
}


@Composable
private fun MainScreen(
    onNavigation: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize(),
        state = state,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(ExampleDestinations.entries) { index, entry ->
            if (index != 0) {
                Button(
                    onClick = {
                        onNavigation.invoke(entry.toString())
                    }
                ) {
                    Text(text = entry.description.ifEmpty { entry.toString() })
                }
            }
        }

//        item {
//            Button(onClick = {
//                startActivity(
//                    Intent(this@MainActivity, ArgumentActivity::class.java)
//                )
//            }) {
//                Text(text = "argument")
//            }
//
//            Button(onClick = {
//                startActivity(
//                    Intent(this@MainActivity, BottomSheetDialogActivity::class.java)
//                )
//            }) {
//                Text(
//                    text = "BottomSheetDialog",
//                    modifier = Modifier,
//                    color = Color.White,
//                )
//            }
//            Button(onClick = {
//                startActivity(
//                    Intent(this@MainActivity, ModalBottomSheetActivity::class.java)
//                )
//            }) {
//                Text(
//                    text = "ModalBottomSheet",
//                    modifier = Modifier,
//                    color = Color.White,
//                )
//            }
//
//            Button(onClick = {
//                startActivity(
//                    Intent(this@MainActivity, ViewPagerActivity::class.java)
//                )
//            }) {
//                Text(text = "ViewPagerActivity")
//            }
//
//            Button(onClick = {
//                startActivity(
//                    Intent(this@MainActivity, ViewPager2Activity::class.java)
//                )
//            }) {
//                Text(
//                    text = "ViewPager2",
//                    modifier = Modifier,
//                    color = Color.White,
//                    fontSize = 18.sp
//                )
//            }
//
//            Button(onClick = {
//                startActivity(
//                    Intent(this@MainActivity, FlexibleSheetActivity::class.java)
//                )
//            }) {
//                Text(
//                    text = "FlexibleSheet",
//                    modifier = Modifier,
//                    color = Color.White,
//                    fontSize = 18.sp
//                )
//            }
//
//            Button(onClick = {
//                startActivity(
//                    Intent(this@MainActivity, DraggableActivity::class.java)
//                )
//            }) {
//                Text(
//                    text = "Draggable",
//                    modifier = Modifier,
//                    color = Color.White,
//                    fontSize = 18.sp
//                )
//            }
//
//            Button(onClick = {
//                startActivity(
//                    Intent(this@MainActivity, SwipeableActivity::class.java)
//                )
//            }) {
//                Text(
//                    text = "Swipeable",
//                    modifier = Modifier,
//                    color = Color.White,
//                    fontSize = 18.sp
//                )
//            }
//
//            Button(onClick = {
//                startActivity(
//                    Intent(this@MainActivity, OverflowDetectingActivity::class.java)
//                )
//            }) {
//                Text(
//                    text = "Overflow",
//                    modifier = Modifier,
//                    color = Color.White,
//                    fontSize = 18.sp
//                )
//            }
//
//            Button(onClick = {
//                startActivity(
//                    Intent(this@MainActivity, ConstraintActivity::class.java)
//                )
//            }) {
//                Text(
//                    text = "Constraint",
//                    modifier = Modifier,
//                    color = Color.White,
//                    fontSize = 18.sp
//                )
//            }
//
//            Button(onClick = {
//                startActivity(
//                    Intent(this@MainActivity, FontStyleActivity::class.java)
//                )
//            }) {
//                Text(
//                    text = "FontStyle",
//                    modifier = Modifier,
//                    color = Color.White,
//                    fontSize = 18.sp
//                )
//            }
//
//            Button(onClick = {
//                startActivity(
//                    Intent(this@MainActivity, TextFieldActivity::class.java)
//                )
//            }) {
//                Text(
//                    text = "TextField",
//                    modifier = Modifier,
//                    color = Color.White,
//                    fontSize = 18.sp
//                )
//            }
//
//            Button(onClick = {
//                startActivity(
//                    Intent(this@MainActivity, PerformanceActivity::class.java)
//                )
//            }) {
//                Text(
//                    text = "Performance",
//                    modifier = Modifier,
//                    color = Color.White,
//                    fontSize = 18.sp
//                )
//            }
//            SwipeableSample()
//        }
    }

}