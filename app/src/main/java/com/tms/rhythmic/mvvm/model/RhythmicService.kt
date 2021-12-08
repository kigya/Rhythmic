package com.tms.rhythmic.mvvm.model

interface RhythmicService {
    fun onLoginClicked(name: String, email: String, password: String): Boolean
}