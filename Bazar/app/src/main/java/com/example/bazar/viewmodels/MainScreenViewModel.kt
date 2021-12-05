package com.example.bazar.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bazar.model.User
import com.example.bazar.repository.Repository

class MainScreenViewModel(val context: Context, val repository: Repository) : ViewModel() {
    var token: MutableLiveData<String> = MutableLiveData()
    var user = MutableLiveData<User>()

    init {
        user.value = User()
    }

    fun setToken(token: String?){
        this.token.value = token
    }
}