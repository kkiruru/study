package com.kkiruru.study.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kkiruru.study.compose.ui.ArgumentScreenRoute
import com.kkiruru.study.compose.ui.BackdropScaffoldScreen2Route
import com.kkiruru.study.compose.ui.BackdropScaffoldScreenRoute
import com.kkiruru.study.compose.ui.BottomSheetDialogScreenRoute
import com.kkiruru.study.compose.ui.BottomSheetOnWebViewScreenRoute
import com.kkiruru.study.compose.ui.BottomSheetScaffoldScreenRoute
import com.kkiruru.study.compose.ui.BringIntoViewRoute
import com.kkiruru.study.compose.ui.ConstraintSheetScreenRoute
import com.kkiruru.study.compose.ui.FlexibleSheetScreenRoute
import com.kkiruru.study.compose.ui.FontStyleScreenRoute
import com.kkiruru.study.compose.ui.ModalBottomSheetScreenRoute
import com.kkiruru.study.compose.ui.NestedScrollRoute
import com.kkiruru.study.compose.ui.TransitionScreenRoute
import com.kkiruru.study.compose.ui.coordinator.CoordinatorRoute
import com.kkiruru.study.compose.ui.webview.WebViewActivity


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
    WEB_VIEW("WEB_VIEW"),

    BOTTOM_SHEET_ON_WEB_VIEW("bottomSheet on WebView"),
}

@Composable
fun MainNavHost(
    startDestination: String = ExampleDestinations.MAIN_HOME.toString(),
    navHostController: NavHostController = rememberNavController(),
) {

    val context = LocalContext.current

    NavHost(
        navController = navHostController,
        startDestination = startDestination,
    ) {
        composable(
            ExampleDestinations.MAIN_HOME.toString(),
        ){
            MainScreenRoute(
                onNavigation = {
                    when(it) {
                        ExampleDestinations.WEB_VIEW.toString() -> {
                            WebViewActivity.startActivity(context)
                        }

                        ExampleDestinations.MAIN_HOME.toString() -> {
                            navHostController.navigate(it) {
                                popUpTo(navHostController.graph.id) {
                                    inclusive = false
                                }
                            }
                        }

                        else -> {
                            navHostController.navigate(it)
                        }
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
        composable(
            ExampleDestinations.BOTTOM_SHEET_ON_WEB_VIEW.toString(),
        ){
            BottomSheetOnWebViewScreenRoute()
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
            .navigationBarsPadding()
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
    }
}
