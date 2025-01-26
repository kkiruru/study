package com.kkiruru.study.compose.viewpager

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class ViewPager2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewPagerApp()
        }
    }
}



@Composable
@OptIn(ExperimentalPagerApi::class)
private fun ViewPagerApp() {
    val scope = rememberCoroutineScope()

    val pages = listOf("페이지1", "페이지2")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
    ) {

        val pagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()
        Text(
            text = "${pagerState.currentPage}",
            modifier = Modifier,
            color = Color.Black,
            fontSize = 18.sp
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "${pagerState.currentPage}",
                modifier = Modifier,
                color = Color.Black,
                fontSize = 18.sp
            )
        }

        HorizontalPager(
            count = pages.size,
            state = pagerState,
            userScrollEnabled = false,
        ) { page ->
            if (page == 0) {
                FirstContents(
                    page,
                    onNext = {
                        scope.launch {
                            pagerState.animateScrollToPage(1)
                        }
                    },
                )
            } else {
                SecondContents(
                    page,
                    onPrev = {
                        scope.launch {
                            pagerState.animateScrollToPage(0)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun FirstContents(
    page: Int,
    onNext: () -> Unit,
) {
    Column {
        Text(
            modifier = Modifier.wrapContentSize(),
            text = page.toString(),
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )

        Button(onClick = {
            onNext()
        }) {
            Text(
                text = "Next Page",
                modifier = Modifier,
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }

}

@Composable
private fun SecondContents(
    page: Int,
    onPrev: () -> Unit,
) {
    Column {
        Text(
            modifier = Modifier.wrapContentSize(),
            text = page.toString(),
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )

        Button(onClick = {
            onPrev()
        }) {
            Text(
                text = "Prev Page",
                modifier = Modifier,
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}
