package com.tms.rhythmic.mvp

import android.os.Handler
import android.os.Looper

class RhythmicMvpPresenter(private val view: RhythmicMvpView) {
    private val rhythmicAuthModel: RhythmicMvpAuthModel = RhythmicMvpAuthModel()
    fun onLoginClicked(name: String, email: String, password: String) {
        Handler(Looper.getMainLooper()).postDelayed({
            val isSuccess = rhythmicAuthModel.onLoginClicked(name, email, password)
            view.onLoginResult(isSuccess)
        }, 2000)
    }

    fun onGuestClicked() {
        Handler(Looper.getMainLooper()).postDelayed({
            val isSuccess = true
            view.onLoginResult(isSuccess)
        }, 2000)
    }
}