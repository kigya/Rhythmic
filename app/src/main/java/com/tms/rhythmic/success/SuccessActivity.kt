package com.tms.rhythmic.success

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tms.rhythmic.R

class SuccessActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_success)
    }
}