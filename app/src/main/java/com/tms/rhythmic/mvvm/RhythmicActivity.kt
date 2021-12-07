package com.tms.rhythmic.mvvm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import com.tms.rhythmic.R
import com.tms.rhythmic.success.SuccessActivity

class RhythmicActivity : AppCompatActivity() {
    private lateinit var progress: ProgressBar
    private lateinit var overlayBlack: View
    private lateinit var viewModel: RhythmicViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_rhythmic)

        viewModel = ViewModelProvider(this)[RhythmicViewModel::class.java]

        val loginButton: ImageButton = findViewById(R.id.next_button)
        val guestButton: AppCompatButton = findViewById(R.id.guest_button)
        val name: TextInputLayout = findViewById(R.id.name_field)
        val email: TextInputLayout = findViewById(R.id.email_field)
        val password: TextInputLayout = findViewById(R.id.password_field)
        progress = findViewById(R.id.progress)
        overlayBlack = findViewById(R.id.overlay)

        loginButton.setOnClickListener {
            val nameText: String = name.editText?.text.toString()
            val emailText: String = email.editText?.text.toString()
            val passwordText: String = password.editText?.text.toString()
            viewModel.onLoginClicked(nameText, emailText, passwordText)
        }

        guestButton.setOnClickListener {
            showProgress()
            viewModel.onGuestClicked()
        }
        subscribeOnLiveData()
    }

    private fun subscribeOnLiveData() {
        viewModel.isLoginSuccessLiveData.observe(this, {
            val intent = Intent(this, SuccessActivity::class.java)
            startActivity(intent)
        })
        viewModel.isLoginFailedLiveData.observe(this, {
            Toast.makeText(
                this, "Something went wrong. Please, retry!",
                Toast.LENGTH_LONG
            ).show()
        })
        viewModel.showProgressLiveData.observe(this, {
            showProgress()
        })
        viewModel.hideProgressLiveData.observe(this, {
            hideProgress()
        })
    }

    private fun hideProgress() {
        progress.isVisible = false
        overlayBlack.isVisible = false
    }

    private fun showProgress() {
        progress.isVisible = true
        overlayBlack.isVisible = true
    }
}

