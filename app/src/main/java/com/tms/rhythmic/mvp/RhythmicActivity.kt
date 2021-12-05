package com.tms.rhythmic.mvp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputLayout
import com.tms.rhythmic.R
import com.tms.rhythmic.success.SuccessActivity

class RhythmicActivity : AppCompatActivity(), RhythmicView {

    private val rhythmicPresenter: RhythmicPresenter = RhythmicPresenter(this)
    lateinit var progress: ProgressBar
    lateinit var overlayBlack: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_rhythmic)

        val loginButton: ImageButton = findViewById(R.id.next_button)
        val guestButton: AppCompatButton = findViewById(R.id.guest_button)
        val name: TextInputLayout = findViewById(R.id.name_field)
        val email: TextInputLayout = findViewById(R.id.email_field)
        val password: TextInputLayout = findViewById(R.id.password_field)
        progress = findViewById(R.id.progress)
        overlayBlack = findViewById(R.id.overlay)

        loginButton.setOnClickListener {
            progress.isVisible = true
            overlayBlack.isVisible = true
            val nameText: String = name.editText?.text.toString()
            val emailText: String = email.editText?.text.toString()
            val passwordText: String = password.editText?.text.toString()
            rhythmicPresenter.onLoginClicked(nameText, emailText, passwordText)
        }

        guestButton.setOnClickListener {
            progress.isVisible = true
            overlayBlack.isVisible = true
            rhythmicPresenter.onGuestClicked()
        }
    }

    override fun onLoginResult(isSuccessful: Boolean) {
        progress.isVisible = false
        overlayBlack.isVisible = false
        if (isSuccessful) {
            val intent = Intent(this, SuccessActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(
                this, "Something went wrong. Please, retry!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

}