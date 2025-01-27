package com.kkiruru.kmp.hello

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kkiruru.kmp.hello.intro.IntroScreenRoute
import com.kkiruru.kmp.hello.main.MainScreenRoute
import io.github.aakira.napier.Napier
import org.jetbrains.compose.ui.tooling.preview.Preview


object AppMainDestinations {
    const val Intro = "Intro"
    const val Main = "Main"
}

open class Route(
    val route: String
)
//
//sealed class AppRoute {
//    data object Intro: Route("intro")
//    data object Main: Route("Main") {
//        data object Home: Route("Home")
//        data object Store: Route("Store")
//        data object Laundry: Route("Laundry")
//        data object My: Route("My")
//    }
//}

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
        startDestination = AppMainDestinations.Intro,
    ) {
        composable(AppMainDestinations.Intro) {
            IntroScreenRoute(
                navHostController = navHostController,
                onNext = {
                    Napier.e(">>>>>> onNext > navHostController.navigate(AppRoute.Main.route)")
                    navHostController.navigate(AppMainDestinations.Main) {
                        popUpTo(AppMainDestinations.Intro) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(AppMainDestinations.Main) {
            MainScreenRoute(
//                navHostController = navHostController,
            )
        }
    }
}