package com.kkiruru.study.compose.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

class FontStyleActivity : ComponentActivity() {


    //https://android-developers.googleblog.com/2022/05/whats-new-in-jetpack-compose.html
    //https://medium.com/androiddevelopers/fixing-font-padding-in-compose-text-768cd232425b
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box {

                var initPosition by remember { mutableStateOf(1.0f) }
                val animatePosition by animateFloatAsState(
                    targetValue = initPosition,
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                )

                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val (spacer, button1, text) = createRefs()

                    Column(
                        modifier = Modifier
                            .constrainAs(spacer) {}
                            .fillMaxHeight()
                    ) {
                        Log.e("Transition", " $animatePosition")
                        if (0 < animatePosition) {
                            Spacer(
                                modifier = Modifier
                                    .weight(animatePosition)
                                    .width(20.dp)
                                    .background(color = Color.Blue)
                            )
                        }

                        Text("Hello", Modifier)

                        if (animatePosition == 0.0f) {
                            Text("World", Modifier)
                        }

                        Spacer(
                            modifier = Modifier
                                .weight(1.0f)
                                .width(20.dp)
                                .background(color = Color.Green)
                        )
                    }

                    Button(
                        onClick = { initPosition = 0.0f },
                        modifier = Modifier.constrainAs(button1) {
                            bottom.linkTo(parent.bottom, margin = 20.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    ) {
                        Text("Button 1")
                    }
                }
            }
        }
    }
}