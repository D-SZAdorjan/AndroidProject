package com.example.bazar.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazar.App
import com.example.bazar.model.AddProductRequest
import com.example.bazar.repository.Repository
import kotlinx.coroutines.launch

class AddProductViewModel(val context: Context, val repository: Repository) : ViewModel() {
    var product : MutableLiveData<AddProductRequest> = MutableLiveData()
    var successfullUpload : MutableLiveData<Boolean> = MutableLiveData()
    var creationMessage : String = ""

    init{
        product.value = AddProductRequest("","",0.0,"",false,"", "")
        successfullUpload.value = false
    }

    suspend fun uploadProductToDatabase() {
        val request =
            AddProductRequest(title = product.value!!.title, description = product.value!!.description,
                price_per_unit = product.value!!.price_per_unit.toDouble(), units = product.value!!.units,
                is_active = product.value!!.is_active, amount_type = product.value!!.amount_type,
                price_type = product.value!!.price_type)
        viewModelScope.launch {
            try {
                Log.d("xxx", "AddProductViewModel - User token: ${App.token}")
                val result =
                    repository.addProductToDatabase(App.token, request)
                successfullUpload.value = true
                creationMessage = result.creation
                Log.d("xxx", "AddendProductViewModel - #creation:  $creationMessage")
            }catch(e: Exception){
                Log.d("xxx", "AddProductViewModel exception: $e")
            }
        }
    }
}