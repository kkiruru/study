package com.kkiruru.kmp.hello

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview


sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val screenRoute: String,
) {
    object Home : BottomNavItem("홈", Icons.Default.Home, "HOME")
    object Store : BottomNavItem("스토어", Icons.Default.ShoppingCart, "STORE")
    object PickUp : BottomNavItem("수거신청", Icons.Default.Phone, "PICKUP")
    object My : BottomNavItem("My", Icons.Default.Face, "MY")
}

private val navItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Store,
    BottomNavItem.PickUp,
    BottomNavItem.My,
)

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navHostController = rememberNavController()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomAppBarContainer(
                    items = navItems,
                    onItemClick = { currentNavigationItem ->
                        navHostController.navigate(currentNavigationItem.screenRoute) {
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
                NavigationGraph(navHostController = navHostController)
            }
        }
    }
}


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
                selected = currentRoute == item.screenRoute,
                onClick = { onItemClick(item) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = if (item.screenRoute == currentRoute) MaterialTheme.typography.h6
                            else MaterialTheme.typography.body1,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
            )

        }

    }
}

@Composable
fun NavigationGraph(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = BottomNavItem.Home.screenRoute,
    ) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen(1)
        }
        composable(BottomNavItem.Store.screenRoute) {
            StoreScreen(2)
        }
        composable(BottomNavItem.PickUp.screenRoute) {
            LaundryScreen(3)
        }
        composable(BottomNavItem.My.screenRoute) {
            MyScreen(4)
        }
    }
}