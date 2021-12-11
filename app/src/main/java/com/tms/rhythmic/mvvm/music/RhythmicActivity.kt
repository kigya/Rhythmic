package com.tms.rhythmic.mvvm.music

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import com.tms.rhythmic.R
import com.tms.rhythmic.success.SuccessActivity

class RhythmicActivity : AppCompatActivity() {
    private lateinit var progress: ProgressBar
    private lateinit var overlayBlack: View
    private lateinit var viewModel: RhythmicViewModel
    private lateinit var name: TextInputLayout
    private lateinit var email: TextInputLayout
    private lateinit var password: TextInputLayout
    private lateinit var confirmPassword: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_rhythmic)

        viewModel = ViewModelProvider(this)[RhythmicViewModel::class.java]
        lifecycle.addObserver(viewModel)

        val loginButton: ImageButton = findViewById(R.id.next_button)
        val guestButton: AppCompatButton = findViewById(R.id.guest_button)
        name = findViewById(R.id.name_field)
        email = findViewById(R.id.email_field)
        password = findViewById(R.id.password_field)
        confirmPassword = findViewById(R.id.confirm_password)
        progress = findViewById(R.id.progress)
        overlayBlack = findViewById(R.id.overlay)

        restoreValues()

        name.editText?.addTextChangedListener {
            viewModel.nameLiveData.value = it.toString()
        }

        email.editText?.addTextChangedListener {
            viewModel.emailLiveData.value = it.toString()
        }

        password.editText?.addTextChangedListener {
            viewModel.passwordLiveData.value = it.toString()
        }

        confirmPassword.editText?.addTextChangedListener {
            viewModel.confirmPasswordLiveData.value = it.toString()
        }

        loginButton.setOnClickListener {
            val nameText: String = name.editText?.text.toString()
            val emailText: String = email.editText?.text.toString()
            val passwordText: String = password.editText?.text.toString()
            val confirmPasswordText: String = confirmPassword.editText?.text.toString()
            viewModel.onLoginClicked(nameText, emailText, passwordText, confirmPasswordText)
        }

        guestButton.setOnClickListener {
            showProgress()
            viewModel.onGuestClicked()
        }
        subscribeOnLiveData()
    }

    private fun restoreValues() {
        name.editText?.setText(viewModel.nameLiveData.value ?: "")
        email.editText?.setText(viewModel.emailLiveData.value ?: "")
        password.editText?.setText(viewModel.passwordLiveData.value ?: "")
        confirmPassword.editText?.setText(viewModel.confirmPasswordLiveData.value ?: "")
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

