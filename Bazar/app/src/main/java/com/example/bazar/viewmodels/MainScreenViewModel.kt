package com.example.bazar.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazar.App
import com.example.bazar.model.Product
import com.example.bazar.model.User
import com.example.bazar.model.UserInfo
import com.example.bazar.repository.Repository
import kotlinx.coroutines.launch

class MainScreenViewModel(val context: Context, val repository: Repository) : ViewModel() {
    var token: MutableLiveData<String> = MutableLiveData()
    var user = MutableLiveData<UserInfo>()
    var products : MutableLiveData<List<Product>> = MutableLiveData()

    init {
        user.value = UserInfo("","","","", false, 0)
    }

    fun setToken(token: String?){
        this.token.value = token
    }

    fun getProducts() {
        viewModelScope.launch {
            try {
                val result =
                    repository.getProducts(App.token)
                products.value = result.products
                Log.d("xxx", "ListViewModel - #products:  ${result.item_count}")
            }catch(e: Exception){
                Log.d("xxx", "ListViewMofdel exception: ${e.toString()}")
            }
        }
    }

    fun getThisUser(userName : String) {
        viewModelScope.launch {
            try{
                val result =
                    repository.getUserInfo(userName)
                user.value = result.userData
            }catch ( e: Exception){
                Log.d("xxx", "MainScreenViewModel exception: ${e.toString()}")
            }
        }
    }
}