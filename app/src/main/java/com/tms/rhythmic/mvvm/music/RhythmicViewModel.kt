package com.tms.rhythmic.mvvm.music

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.*
import com.tms.rhythmic.mvvm.model.RhythmicAuthModelService

class RhythmicViewModel: ViewModel(), LifecycleEventObserver {
    val isLoginSuccessLiveData = MutableLiveData<Unit>()
    val isLoginFailedLiveData = MutableLiveData<Unit>()
    val showProgressLiveData = MutableLiveData<Unit>()
    val hideProgressLiveData = MutableLiveData<Unit>()

    val nameLiveData = MutableLiveData<String>()
    val emailLiveData = MutableLiveData<String>()
    val passwordLiveData = MutableLiveData<String>()
    val confirmPasswordLiveData = MutableLiveData<String>()


    private val rhythmicAuthModel: RhythmicAuthModelService = RhythmicAuthModelService()
    fun onLoginClicked(name: String, email: String, password: String, confirmPassword: String) {
        showProgressLiveData.postValue(Unit)
        Handler(Looper.getMainLooper()).postDelayed({
            val isSuccess = rhythmicAuthModel.onLoginClicked(name, email, password, confirmPassword)
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

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_CREATE -> {
                println("onCreate")
            }
            Lifecycle.Event.ON_START -> {
                println("onStart")
            }
            Lifecycle.Event.ON_RESUME -> {
                println("onResume")
            }
            Lifecycle.Event.ON_PAUSE -> {
                println("onPause")
            }
            Lifecycle.Event.ON_DESTROY -> {
                println("onDestroy")
            }
            Lifecycle.Event.ON_STOP -> {
                println("onStop")
            }
            Lifecycle.Event.ON_ANY -> {
                println("onAny")
            }
        }
    }


}