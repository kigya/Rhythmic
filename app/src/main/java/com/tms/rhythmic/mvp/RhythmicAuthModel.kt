package com.tms.rhythmic.mvp

import android.util.Patterns

class RhythmicAuthModel {
    fun onLoginClicked(name: String, email: String, password: String): Boolean {
        val isNameValid = name.isValidName()
        val isEmailValid = email.isValidEmail()
        val isPasswordValid = password.isValidPassword()
        return isNameValid && isEmailValid && isPasswordValid
    }

    private fun String.isValidName() = !isNullOrEmpty()
            && this.split("\\s+").size == 1 && this[0].isUpperCase()

    private fun CharSequence?.isValidEmail() = !isNullOrEmpty() &&
            Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun CharSequence?.isValidPassword() = !isNullOrEmpty() && this.length > 5

}