package com.kkiruru.study.compose.ui

import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun TransitionScreenRoute() {
    TransitionScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TransitionScreen(
) {

    Box {

        var initPosition by remember { mutableStateOf(1.0f) }
        val animatePosition by animateFloatAsState(
            targetValue = initPosition,
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
            label = ""
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