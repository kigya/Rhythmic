package com.tms.rhythmion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        hideStatusBar()
        setContentView(R.layout.activity_main)
    }

    @Suppress("DEPRECATION")
    private fun hideStatusBar() {
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}