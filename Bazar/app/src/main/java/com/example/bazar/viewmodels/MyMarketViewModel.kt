package com.example.bazar.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazar.App
import com.example.bazar.model.Product
import com.example.bazar.model.UserInfo
import com.example.bazar.repository.Repository
import kotlinx.coroutines.launch

class MyMarketViewModel (val context: Context, val repository: Repository) : ViewModel() {
    var user = MutableLiveData<UserInfo>()
    var products : MutableLiveData<List<Product>> = MutableLiveData()
    var successfulldelete : MutableLiveData<Boolean> = MutableLiveData()

    init {
        user.value = UserInfo("","","","", false, 0)
        successfulldelete.value = false
    }

    fun getUserProducts() {
        viewModelScope.launch {
            try {
                Log.d("xxx", "MyMarketViewModel - User: ${App.thisUser.username}")
                val result =
                    repository.getFilteredProducts(App.token, "{\"username\": \"${App.thisUser.username}\"}")
                    //repository.getFilteredProducts(App.token, "{\"username\": \"demen\"}")
                products.value = result.products
                Log.d("xxx", "myMarketViewModel - #products:  ${result.item_count}")
            }catch(e: Exception){
                Log.d("xxx", "myMarketViewModel exception: ${e.toString()}")
            }
        }
    }

    fun deleteUserProduct(prodId: String) {
        viewModelScope.launch {
            try {
                Log.d("xxx", "MyMarketViewModel (remove product) - User: ${App.thisUser.username}")
                val result =
                    repository.removeProduct(App.token, prodId)
                Log.d("xxx", "myMarketViewModel (remove product) - #removedproductMessage:  ${result.message}")
                //Toast.makeText(context, "Product successfully deleted!\nPlease reload the page!", Toast.LENGTH_LONG)
                successfulldelete.value = true
            }catch(e: Exception){
                successfulldelete.value = false
                Log.d("xxx", "myMarketViewModel (remove product) exception: ${e.toString()}")
                //Toast.makeText(context, "Product couldn't be deleted!\nError: $e", Toast.LENGTH_LONG)
            }
        }
    }

}