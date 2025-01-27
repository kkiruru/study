package com.kkiruru.kmp.hello

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.kkiruru.kmp.hello.intro.IntroScreenRoute
import com.kkiruru.kmp.hello.main.MainScreenRoute
import io.github.aakira.napier.Napier
import org.jetbrains.compose.ui.tooling.preview.Preview


object AppMainDestinations {
    const val INTRO = "INTRO"
    const val MAIN = "MAIN"
}

open class Route(
    val route: String
)

sealed class AppRoute {
    data object Intro: Route("intro")
    data object Main: Route("Main") {
        data object Home: Route("Home")
        data object Store: Route("Store")
        data object Laundry: Route("Laundry")
        data object My: Route("My")
    }
}

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navHostController = rememberNavController()
        NavigationGraph(
            navHostController = navHostController,
        )
    }
}

@Composable
fun NavigationGraph(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navHostController,
        startDestination = AppRoute.Intro.route,
    ) {
        composable(AppRoute.Intro.route) {
            IntroScreenRoute(
                navHostController = navHostController,
                onNext = {
//                        Napier.e("Napier Error", e)
                    Napier.e(">>>>>> onNext > navHostController.navigate(AppRoute.Main.route)")
                    navHostController.navigate(AppRoute.Main.route) {
//                            popUpTo(AppRoute.Intro.route) {
//                                inclusive = true
//                            }
                    }
                }
            )
        }

        navigation(
            startDestination = AppRoute.Main.Home.route,
            route = AppRoute.Main.route
        ) {
            composable(AppRoute.Main.Home.route) {
                MainScreenRoute(
                    navHostController = navHostController,
                )
            }
            composable(AppRoute.Main.Store.route) {
                Box(
                    modifier = Modifier.fillMaxSize().background(Color.Yellow),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Store"
                    )
                }
            }
        }

    }
}