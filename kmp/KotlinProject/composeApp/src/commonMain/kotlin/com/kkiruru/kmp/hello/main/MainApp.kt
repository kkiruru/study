package com.kkiruru.kmp.hello.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.kkiruru.kmp.hello.navSavedStateConfiguration
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_menu_home_activated
import kotlinproject.composeapp.generated.resources.ic_menu_home_default
import kotlinproject.composeapp.generated.resources.ic_menu_laundry_activated
import kotlinproject.composeapp.generated.resources.ic_menu_laundry_default
import kotlinproject.composeapp.generated.resources.ic_menu_my_activated
import kotlinproject.composeapp.generated.resources.ic_menu_my_default
import kotlinproject.composeapp.generated.resources.ic_menu_store_activated
import kotlinproject.composeapp.generated.resources.ic_menu_store_default
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Serializable
sealed interface MainRoute : NavKey {
    @Serializable
    data object Home : MainRoute

    @Serializable
    data object Store : MainRoute

    @Serializable
    data object Laundry : MainRoute

    @Serializable
    data object My : MainRoute
}

@Serializable
sealed interface SubRoute : NavKey {
    @Serializable
    data class Content(val count: Int) : SubRoute

    @Serializable
    data class Child(val count: Int) : SubRoute
}


sealed class BottomNavItem(
    val title: String,
    val route: MainRoute,
    val selectedIcon: DrawableResource,
    val unSelectedIcon: DrawableResource,
) {
    object Home : BottomNavItem("홈", MainRoute.Home, Res.drawable.ic_menu_home_activated, Res.drawable.ic_menu_home_default)
    object Store : BottomNavItem("스토어", MainRoute.Store, Res.drawable.ic_menu_store_activated, Res.drawable.ic_menu_store_default)
    object Laundry : BottomNavItem("수거신청", MainRoute.Laundry, Res.drawable.ic_menu_laundry_activated, Res.drawable.ic_menu_laundry_default)
    object My : BottomNavItem("My", MainRoute.My, Res.drawable.ic_menu_my_activated, Res.drawable.ic_menu_my_default)
}

private val navItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Store,
    BottomNavItem.Laundry,
    BottomNavItem.My,
)

private val subSavedStateConfiguration = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(SubRoute.Content::class)
            subclass(SubRoute.Child::class)
        }
    }
}

@Composable
fun SubScreenNavHost(
    count: Int,
    onNavigateToSub: (Int) -> Unit,
) {
    val subBackStack = rememberNavBackStack(subSavedStateConfiguration, SubRoute.Content(count))

    NavDisplay(
        backStack = subBackStack,
        entryProvider = entryProvider {
            entry<SubRoute.Content> { route ->
                SubScreen(
                    count = route.count,
                    onNavigateSub = { nextCount -> subBackStack.add(SubRoute.Child(nextCount)) }
                )
            }
            entry<SubRoute.Child> { route ->
                SubScreenNavHost(
                    count = route.count,
                    onNavigateToSub = onNavigateToSub,
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onNavigateToSub: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val backStack = rememberNavBackStack(navSavedStateConfiguration, MainRoute.Home)
    var selectedTab by rememberSaveable { mutableStateOf(MainRoute.Home::class.simpleName ?: "Home") }
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBarContainer(
                items = navItems,
                selectedTab = selectedTab,
                onItemClick = { item ->
                    selectedTab = item.route::class.simpleName ?: ""
                    backStack.clear()
                    backStack.add(item.route)
                },
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            NavDisplay(
                backStack = backStack,
                entryProvider = entryProvider {
                    entry<MainRoute.Home> {
                        HomeScreen()
                    }
                    entry<MainRoute.Store> {
                        StoreScreen(
                            onNavigateToSub = {
                                showBottomSheet = true
                            }
                        )
                    }
                    entry<MainRoute.Laundry> {
                        LaundryScreen()
                    }
                    entry<MainRoute.My> {
                        MyScreen()
                    }
                }
            )
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            dragHandle = null,
            modifier = Modifier.fillMaxSize(),
        ) {
            SubScreenNavHost(
                count = 1,
                onNavigateToSub = { nextCount ->
                    onNavigateToSub(nextCount)
                },
            )
        }
    }
}


@Composable
fun BottomAppBarContainer(
    onItemClick: (BottomNavItem) -> Unit,
    items: List<BottomNavItem>,
    selectedTab: String,
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
    ) {
        items.forEach { item ->
            val isSelected = selectedTab == (item.route::class.simpleName ?: "")
            NavigationBarItem(
                selected = isSelected,
                onClick = { onItemClick(item) },
                icon = {
                    Icon(
                        modifier = Modifier.wrapContentSize(),
                        painter = painterResource(if (isSelected) item.selectedIcon else item.unSelectedIcon),
                        contentDescription = item.title,
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = if (isSelected) MaterialTheme.typography.h6
                        else MaterialTheme.typography.body1,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
            )
        }
    }
}
