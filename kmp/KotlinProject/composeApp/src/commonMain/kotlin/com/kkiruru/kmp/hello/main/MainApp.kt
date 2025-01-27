package com.kkiruru.kmp.hello.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.kkiruru.kmp.hello.Route
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_menu_home_activated
import kotlinproject.composeapp.generated.resources.ic_menu_home_default
import kotlinproject.composeapp.generated.resources.ic_menu_laundry_activated
import kotlinproject.composeapp.generated.resources.ic_menu_laundry_default
import kotlinproject.composeapp.generated.resources.ic_menu_my_activated
import kotlinproject.composeapp.generated.resources.ic_menu_my_default
import kotlinproject.composeapp.generated.resources.ic_menu_store_activated
import kotlinproject.composeapp.generated.resources.ic_menu_store_default
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
fun MainScreenRoute(
    navHostController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBarContainer(
                items = navItems,
                onItemClick = { currentNavigationItem ->
                    navHostController.navigate(currentNavigationItem.route) {
                        popUpTo(navHostController.graph.startDestinationRoute ?: "") {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                navHostController = navHostController)
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            NavHost(
                navController = navHostController,
                startDestination = Main.route,
                Modifier.padding(innerPadding)
            ) {
                navigation(
                    startDestination = BottomNavItem.Home.route,
                    route = Main.route
                ) {
                    composable(BottomNavItem.Home.route) {
                        HomeScreen()
                    }
                    composable(BottomNavItem.Store.route) {
                        StoreScreen()
                    }
                    composable(BottomNavItem.Laundry.route) {
                        LaundryScreen()
                    }
                    composable(BottomNavItem.My.route) {
                        MyScreen()
                    }
                }
            }
        }
    }
}


data object Main: Route("Main") {
    data object Home: Route("Home")
    data object Store: Route("Store")
    data object Laundry: Route("Laundry")
    data object My: Route("My")
}


sealed class BottomNavItem(
    val title: String,
    val route: String,
    val selectedIcon: DrawableResource,
    val unSelectedIcon: DrawableResource,
) {
    object Home : BottomNavItem("홈", "Home", Res.drawable.ic_menu_home_activated, Res.drawable.ic_menu_home_default)
    object Store : BottomNavItem("스토어", "Store", Res.drawable.ic_menu_store_activated, Res.drawable.ic_menu_store_default)
    object Laundry : BottomNavItem("수거신청", "Laundry", Res.drawable.ic_menu_laundry_activated, Res.drawable.ic_menu_laundry_default)
    object My : BottomNavItem("My", "My", Res.drawable.ic_menu_my_activated, Res.drawable.ic_menu_my_default)
}

private val navItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Store,
    BottomNavItem.Laundry,
    BottomNavItem.My,
)

@Composable
fun BottomAppBarContainer(
    onItemClick: (BottomNavItem) -> Unit,
    items: List<BottomNavItem>,
    navHostController: NavHostController,
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { onItemClick(item) },
                icon = {
                    Icon(
                        modifier = Modifier.wrapContentSize(),
                        painter = painterResource(if (item.route == currentRoute) item.selectedIcon else item.unSelectedIcon),
                        contentDescription = item.title,
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = if (item.route == currentRoute) MaterialTheme.typography.h6
                        else MaterialTheme.typography.body1,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
            )
        }
    }
}