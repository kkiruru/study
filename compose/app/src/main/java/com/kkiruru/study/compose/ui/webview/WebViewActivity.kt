package com.kkiruru.study.compose.ui.webview

import android.content.Context
import android.content.Intent
import android.net.http.SslError
import android.os.Bundle
import android.webkit.SslErrorHandler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kkiruru.study.compose.R


class WebViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bring_into_view)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                WebViewApp()
            }
        }
    }

    companion object {
        fun startActivity(
            context: Context,
        ) {
            context.startActivity(
                Intent(context, WebViewActivity::class.java).apply {
                }
            )
        }
    }
}


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

internal object LocalWebOwner {
    private val LocalComposition = staticCompositionLocalOf<WebView?> { null }

    val current: WebView?
        @Composable
        get() = LocalComposition.current

    infix fun provides(registerOwner: WebView?): ProvidedValue<WebView?> =
        LocalComposition provides registerOwner
}

@Composable
fun NavigationGraph(
    navHostController: NavHostController,
) {
    val context = LocalContext.current

    CompositionLocalProvider(
        LocalWebOwner provides WebView(context).apply {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
            webViewClient = object : WebViewClient() {
                override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                    handler?.proceed()
                }
            }
            loadUrl("https://m.naver.com")
        }
    ) {
        NavHost(
            navController = navHostController,
            startDestination = BottomNavItem.Home.screenRoute,
        ) {
            composable(BottomNavItem.Home.screenRoute) {
                MainScreen(1)
            }
            composable(BottomNavItem.Store.screenRoute) {
                StoreScreenRoute()
            }
            composable(BottomNavItem.PickUp.screenRoute) {
                LaundryScreen(3)
            }
            composable(BottomNavItem.My.screenRoute) {
                MyScreen(4)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WebViewApp() {
    MaterialTheme {
        val navHostController = rememberNavController()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomAppBar(navHostController = navHostController)
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
fun BottomAppBar(
    navHostController: NavHostController,
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomAppBar(containerColor = Color.White, contentColor = Color.Gray) {
        navItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = "") },
                label = {
                    Text(
                        text = item.title
                    )
                },
                alwaysShowLabel = true,
                selected = currentRoute == item.screenRoute,
                onClick = {
                    navHostController.navigate(item.screenRoute) {
                        navHostController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
            )
        }
    }
}

