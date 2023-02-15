package com.kkiruru.study.compose

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kkiruru.study.compose.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bringIntoView.setOnClickListener {
            startActivity(
                Intent(this, BringIntoViewActivity::class.java)
            )
        }

//        binding.dialog.setOnClickListener {
//            val itemListDialog = CustomBottomSheetDialogFragment.newInstance(5)
//            itemListDialog.show(
//                supportFragmentManager,
//                CustomBottomSheetDialogFragment.TAG)
//        }

    }
}