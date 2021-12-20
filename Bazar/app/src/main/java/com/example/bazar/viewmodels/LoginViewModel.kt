package com.example.bazar.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bazar.App
import com.example.bazar.manager.SharedPreferencesManager
import com.example.bazar.model.LoginRequest
import com.example.bazar.model.User
import com.example.bazar.repository.Repository

class LoginViewModel(val context: Context, val repository: Repository) : ViewModel() {
    var token: MutableLiveData<String> = MutableLiveData()
    var user = MutableLiveData<User>()
    var isSuccessfull : MutableLiveData<Boolean> = MutableLiveData()

    init {
        user.value = User()
    }

//    fun login() {
//        viewModelScope.launch {
//            val request =
//                LoginRequest(username = user.value!!.username, password = user.value!!.password)
//            try {
//                val result = repository.login(request)
//                MyApplication.token = result.token
//                token.value = result.token
//                Log.d("xxx", "MyApplication - token:  ${MyApplication.token}")
//            }catch(e: Exception){
//                Log.d("xxx", "MainViewModel - exception: ${e.toString()}")
//            }
//        }
//    }

    suspend fun login() {
        val request =
            LoginRequest(username = user.value!!.username, password = user.value!!.password)
        try {
            val result = repository.login(request)
            App.token = result.token
            App.thisUser.username = result.username
            App.thisUser.email = result.email
            App.thisUser.creation_time = result.creation_time
            App.sharedPreferences.putStringValue(SharedPreferencesManager.KEY_TOKEN,result.token)
            isSuccessfull.value = true
            token.value = result.token
            Log.d("xxx", "MyApplication - token:  ${App.token}")
        } catch (e: Exception) {
            Log.d("xxx", "LoginViewModel - exception: ${e.toString()}")
            isSuccessfull.value = false
        }
    }
}