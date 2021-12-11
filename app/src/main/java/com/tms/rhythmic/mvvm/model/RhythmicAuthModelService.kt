package com.tms.rhythmic.mvvm.model

import android.util.Patterns

class RhythmicAuthModelService: RhythmicService{
    override fun onLoginClicked(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        val isNameValid = name.isValidName()
        val isEmailValid = email.isValidEmail()
        val isPasswordValid = password.isValidPassword(confirmPassword)
        return isNameValid && isEmailValid && isPasswordValid
    }

    private fun String.isValidName(): Boolean = !isNullOrEmpty()
            && this.split("\\s+").size == 1 && this[0].isUpperCase()

    private fun CharSequence?.isValidEmail(): Boolean = !isNullOrEmpty() &&
            Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun CharSequence?.isValidPassword(confirmPassword: String): Boolean =
        !isNullOrEmpty() && this.length > 5 && confirmPassword == this
}