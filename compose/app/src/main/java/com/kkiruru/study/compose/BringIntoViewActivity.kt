package com.kkiruru.study.compose

import android.os.Bundle
import androidx.activity.ComponentActivity

class BringIntoViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bring_into_view)
    }
}