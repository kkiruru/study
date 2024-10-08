package com.kkiruru.study.compose.viewpager


import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.kkiruru.study.compose.databinding.ComposeFragmentPurchaseBinding
import kotlinx.coroutines.launch

class ViewPagerActivity : AppCompatActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModalBottomSheetSample()
//            MyUI()
        }
    }

}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun ModalBottomSheetSample() {
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = {
            LazyColumn {
                items(5) {
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
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()

    ) {
        ViewPagerApp()
    }
}


@Composable
@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
private fun ViewPagerApp() {
    val scope = rememberCoroutineScope()

    val pages = listOf("페이지1", "페이지2")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {

        val pagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.White,

            ) {
            pages.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(index)
                        }
                    },
                    text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                )
            }
        }

        HorizontalPager(
            count = pages.size,
            state = pagerState,
        ) { page ->
            if (page == 0) {
                FirstContents(page)
            } else {
                FragmentContents(page)
            }


        }
    }
}


@Composable
private fun FirstContents(page: Int) {
    Text(
        modifier = Modifier.wrapContentSize(),
        text = page.toString(),
        textAlign = TextAlign.Center,
        fontSize = 30.sp
    )
}


@Composable
private fun FragmentContents(page: Int) {
    AndroidViewBinding(ComposeFragmentPurchaseBinding::inflate) {
        val myFragment = fragmentContainerView.getFragment<PurchaseFragment>()
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MyUI() {
    val contextForToast = LocalContext.current.applicationContext

    val coroutineScope = rememberCoroutineScope()

    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 156.dp,
        sheetContent = {

            LazyColumn {

                // the first item that is visible
                item {
                    Box(
                        modifier = Modifier
                            .height(56.dp)
                            .fillMaxWidth()
                            .background(color = MaterialTheme.colors.primary)
                    ) {
                        Text(
                            text = "Swipe up to Expand the sheet",
                            modifier = Modifier.align(alignment = Alignment.Center),
                            color = Color.White
                        )
                    }
                }

                // remaining items
                items(count = 5) {
                    ListItem(
                        modifier = Modifier.clickable {

                            Toast.makeText(contextForToast, "Item $it", Toast.LENGTH_SHORT)
                                .show()

                            coroutineScope.launch {
                                scaffoldState.bottomSheetState.collapse()
                            }
                        },
                        text = {
                            Text(text = "Item $it")
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Favorite",
                                tint = MaterialTheme.colors.primary
                            )
                        }
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                Toast.makeText(contextForToast, "FAB Click", Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Favorite"
                )
            }
        }) {
        // app UI
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Rest of the app UI")
        }
    }
}