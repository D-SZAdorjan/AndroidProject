package com.example.bazar.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazar.App
import com.example.bazar.model.Product
import com.example.bazar.model.UserInfo
import com.example.bazar.repository.Repository
import kotlinx.coroutines.launch

class ProfileFragmentViewModel(val context: Context, val repository: Repository) : ViewModel() {
    var user = MutableLiveData<UserInfo>()

    init {
        user.value = UserInfo("","","","", false, 0)
    }

    fun getUserInformation(wantedUser: String = App.thisUser.username) {
        viewModelScope.launch {
            try {
                Log.d("xxx", "ProfileFragmentViewModel - User: ${App.thisUser.username}")
                val result =
                    repository.getUserInfo(wantedUser)
                Log.d("xxx", "ProfileFragmentViewModel - #result:  ${result.data}")
                user.value = result.data[0]
                Log.d("xxx", "ProfileFragmentViewModel - #errorCode:  ${result.code}")
            }catch(e: Exception){
                Log.d("xxx", "ProfileFragmentViewModel exception: ${e.toString()}")
            }
        }
    }

}