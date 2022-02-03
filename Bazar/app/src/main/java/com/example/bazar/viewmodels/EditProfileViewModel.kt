package com.example.bazar.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazar.App
import com.example.bazar.model.AddProductRequest
import com.example.bazar.model.EditProfileRequest
import com.example.bazar.model.UserInfo
import com.example.bazar.repository.Repository
import kotlinx.coroutines.launch

class EditProfileViewModel(val context: Context, val repository: Repository) : ViewModel() {
    var user : MutableLiveData<EditProfileRequest> = MutableLiveData()
    var successfullUpdate : MutableLiveData<Boolean> = MutableLiveData()
    var errorCode : String = ""

    init{
        user.value = EditProfileRequest("","", 0)
    }

    suspend fun updateProfileData() {
        val request =
            EditProfileRequest( user.value!!.email, user.value!!.username, user.value!!.phone_number)
        viewModelScope.launch {
            try {
                Log.d("xxx", "EditProfileViewModel - User token: ${App.token}")
                val result =
                    repository.editProfile(App.token, request)
                App.thisUser = result.updatedData
                successfullUpdate.value = true
                errorCode = result.code.toString()
                Log.d("xxx", "EditProfileViewModel - #errorCode:  $errorCode")
            }catch(e: Exception){
                Log.d("xxx", "EditProfileViewModel exception: $e")
            }
        }
    }
}