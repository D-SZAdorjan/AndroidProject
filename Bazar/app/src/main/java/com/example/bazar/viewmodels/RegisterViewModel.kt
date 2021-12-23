package com.example.bazar.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bazar.App
import com.example.bazar.manager.SharedPreferencesManager
import com.example.bazar.model.LoginRequest
import com.example.bazar.model.RegisterRequest
import com.example.bazar.model.User
import com.example.bazar.repository.Repository

class RegisterViewModel (val context: Context, val repository: Repository) : ViewModel() {
    var user = MutableLiveData<User>()
    var isSuccessfull : MutableLiveData<Boolean> = MutableLiveData()
    var message : String = ""

    init {
        user.value = User()
    }

    suspend fun register() {
        val request =
            RegisterRequest(username = user.value!!.username, password = user.value!!.password, email= user.value!!.email)
        try {
            val result = repository.register(request)
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