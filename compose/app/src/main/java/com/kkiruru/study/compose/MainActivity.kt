package com.kkiruru.study.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.kkiruru.study.compose.viewpager.ViewPagerActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bring_into_view)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier.statusBarsPadding().fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Button(onClick = {
                        startActivity(
                            Intent(this@MainActivity, BringIntoViewActivity::class.java)
                        )
                    }) {
                        Text(text = "bring Into View")
                    }

                    Button(onClick = {
                        startActivity(
                            Intent(this@MainActivity, NestedScrollActivity::class.java)
                        )
                    }) {
                        Text(text = "nested")
                    }

                    Button(onClick = {
                        startActivity(
                            Intent(this@MainActivity, TransitionActivity::class.java)
                        )
                    }) {
                        Text(text = "transition")
                    }

                    Button(onClick = {
                        startActivity(
                            Intent(this@MainActivity, BackdropScaffoldActivity::class.java)
                        )
                    }) {
                        Text(text = "backDrop")
                    }

                    Button(onClick = {
                        startActivity(
                            Intent(this@MainActivity, BackdropScaffold2Activity::class.java)
                        )
                    }) {
                        Text(text = "backDrop2")
                    }

                    Button(onClick = {
                        startActivity(
                            Intent(this@MainActivity, ArgumentActivity::class.java)
                        )
                    }) {
                        Text(text = "argument")
                    }

                    Button(onClick = {
                        startActivity(
                            Intent(this@MainActivity, BottomSheetDialogActivity::class.java)
                        )
                    }) {
                        Text(
                            text = "BottomSheetDialog",
                            modifier = Modifier,
                            color = Color.White,
                        )
                    }

                    Button(onClick = {
                        startActivity(
                            Intent(this@MainActivity, ViewPagerActivity::class.java)
                        )
                    }) {
                        Text(text = "ViewPagerActivity")
                    }

                    Button(onClick = {
                        startActivity(
                            Intent(this@MainActivity, FlexibleSheetActivity::class.java)
                        )
                    }) {
                        Text(
                            text = "FlexibleSheet",
                            modifier = Modifier,
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }

                    Button(onClick = {
                        startActivity(
                            Intent(this@MainActivity, DraggableActivity::class.java)
                        )
                    }) {
                        Text(
                            text = "Draggable",
                            modifier = Modifier,
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }

                    Button(onClick = {
                        startActivity(
                            Intent(this@MainActivity, SwipeableActivity::class.java)
                        )
                    }) {
                        Text(
                            text = "Swipeable",
                            modifier = Modifier,
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}
