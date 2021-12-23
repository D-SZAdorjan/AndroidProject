package com.example.bazar.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bazar.model.ForgotPasswordRequest
import com.example.bazar.model.RegisterRequest
import com.example.bazar.model.User
import com.example.bazar.repository.Repository

class ForgotPasswordViewModel(val context: Context, val repository: Repository) : ViewModel() {
    var user = MutableLiveData<User>()
    var isSuccessfull : MutableLiveData<Boolean> = MutableLiveData()
    var message : String = ""

    init {
        user.value = User()
    }

    suspend fun sendPasswordResetEmail() {
        val request =
            ForgotPasswordRequest(username = user.value!!.username, email= user.value!!.email)
        try {
            val result = repository.sendPasswordReset(request)
            message = result.message
            isSuccessfull.value = true
            Log.d("xxx", "RegisterViewModel - error code:  ${result.code}")
            Log.d("xxx", "RegisterViewModel - message:  ${result.message}")
        } catch (e: Exception) {
            Log.d("xxx", "RegisterViewModel - exception: ${e.toString()}")
            isSuccessfull.value = false
        }
    }
}