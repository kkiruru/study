package com.kkiruru.kmp.hello

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.kkiruru.kmp.hello.intro.IntroScreen
import com.kkiruru.kmp.hello.main.MainRoute
import com.kkiruru.kmp.hello.main.MainScreen
import com.kkiruru.kmp.hello.main.SubScreenNavHost
import io.github.aakira.napier.Napier
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.jetbrains.compose.ui.tooling.preview.Preview

@Serializable
sealed interface AppRoute : NavKey {
    @Serializable
    data object Intro : AppRoute

    @Serializable
    data object Main : AppRoute

    @Serializable
    data class Sub(val count: Int) : AppRoute
}

val navSavedStateConfiguration = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(AppRoute.Intro::class)
            subclass(AppRoute.Main::class)
            subclass(AppRoute.Sub::class)
            subclass(MainRoute.Home::class)
            subclass(MainRoute.Store::class)
            subclass(MainRoute.Laundry::class)
            subclass(MainRoute.My::class)
        }
    }
}

@Composable
@Preview
fun App() {
    MaterialTheme {
        val backStack = rememberNavBackStack(navSavedStateConfiguration, AppRoute.Intro)

        NavDisplay(
            backStack = backStack,
            entryProvider = entryProvider {
                entry<AppRoute.Intro> {
                    IntroScreen(
                        onNext = {
                            Napier.e(">>>>>> onNext > navigate to Main")
                            backStack.clear()
                            backStack.add(AppRoute.Main)
                        }
                    )
                }
                entry<AppRoute.Main> {
                    MainScreen(
                        onNavigateToSub = { count ->
                            backStack.add(AppRoute.Sub(count))
                        }
                    )
                }
                entry<AppRoute.Sub> { route ->
                    SubScreenNavHost(
                        count = route.count,
                        onNavigateToSub = { nextCount ->
                            backStack.add(AppRoute.Sub(nextCount))
                        }
                    )
                }
            }
        )
    }
}
