package com.kkiruru.study.compose.ui.coordinator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest


//CoordinatorLayout 적용 예제
//https://sukzoon1234.tistory.com/42
//https://developer.android.com/develop/ui/compose/migrate/migration-scenarios/coordinator-layout?hl=ko


@Composable
fun CoordinatorRoute(

) {


    CoordinatorScreen(

    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CoordinatorScreen(
    modifier: Modifier = Modifier,
) {
    val imageList = listOf(
        "https://static.laundrygo.com/cms/CKDWWN/202012/cms_hydd8174VXAYx0Z.jpg",
        "https://static.laundrygo.com/cms/CKDWWN/202012/cms_FIsMiLKocVlaClg.jpg",
        "https://static.laundrygo.com/cms/CKDWWN/202012/cms_5K8Utrd5hqgaqWA.jpg"
    )
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded,
            skipHiddenState = true
        ),
    )

    Box(
        modifier = Modifier.fillMaxSize().background(color = Color(0xFFFFFFFF))
    ) {
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetPeekHeight = 0.dp,
            sheetShape = BottomSheetDefaults.ExpandedShape,
            sheetContent = {
                BackLayerScreen()
            },
        ) { contentPadding ->
           FrontLayerScreen(imageList)
        }
    }
}


@Composable
private fun BackLayerScreen(
    modifier: Modifier = Modifier,
) {
    LazyColumn {
        items(5) {
            Foo()
        }
    }
}



@Composable
private fun FrontLayerScreen(
    imageList: List<String>,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState {
        10
    }

    HorizontalPager(
            state = pagerState,
            modifier = Modifier.height(280.dp)
    ) { /* Page contents */
        Banner(
            url = imageList[it % imageList.size],
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
private fun Foo(){
    Button(
        modifier = Modifier.height(80.dp).fillMaxWidth(),
        onClick = {
        },
    ) {
        Text(text = "1")
    }
}




@Composable
private fun Banner(
    url: String,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier,
    )
}
