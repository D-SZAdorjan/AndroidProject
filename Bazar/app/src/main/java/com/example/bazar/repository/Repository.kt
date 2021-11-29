package com.example.bazar.repository

import android.util.Log
import com.example.bazar.api.RetrofitInstance
import com.example.bazar.model.LoginRequest
import com.example.bazar.model.LoginResponse
import com.example.bazar.model.ProductResponse

class Repository {
    suspend fun login(request: LoginRequest): LoginResponse {
        return RetrofitInstance.api.login(request)
    }

    suspend fun getProducts(token: String): ProductResponse {
        return RetrofitInstance.api.getProducts(token)
    }
}