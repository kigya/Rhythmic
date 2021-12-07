package com.tms.rhythmic.mvvm

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RhythmicViewModel: ViewModel() {
    val isLoginSuccessLiveData = MutableLiveData<Unit>()
    val isLoginFailedLiveData = MutableLiveData<Unit>()
    val showProgressLiveData = MutableLiveData<Unit>()
    val hideProgressLiveData = MutableLiveData<Unit>()

    private val rhythmicAuthModel: RhythmicAuthModel = RhythmicAuthModel()
    fun onLoginClicked(name: String, email: String, password: String) {
        showProgressLiveData.postValue(Unit)
        Handler(Looper.getMainLooper()).postDelayed({
            val isSuccess = rhythmicAuthModel.onLoginClicked(name, email, password)
            if (isSuccess) {
                isLoginSuccessLiveData.postValue(Unit)
            } else {
                isLoginFailedLiveData.postValue(Unit)
            }
            hideProgressLiveData.postValue(Unit)
        }, 2000)
    }

    fun onGuestClicked() {
        Handler(Looper.getMainLooper()).postDelayed({
            isLoginSuccessLiveData.postValue(Unit)
        }, 2000)
    }
}