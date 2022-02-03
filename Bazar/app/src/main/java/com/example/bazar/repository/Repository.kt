package com.example.bazar.repository

import android.util.Log
import com.example.bazar.api.RetrofitInstance
import com.example.bazar.model.*

class Repository {
    suspend fun login(request: LoginRequest): LoginResponse {
        return RetrofitInstance.api.login(request)
    }

    suspend fun register(request: RegisterRequest): RegisterResponse{
        return RetrofitInstance.api.register( request )
    }

    suspend fun sendPasswordReset(request: ForgotPasswordRequest): ForgotPasswordResponse{
        return RetrofitInstance.api.sendPasswordReset(request)
    }

    suspend fun getProducts(token: String): ProductResponse {
        return RetrofitInstance.api.getProducts(token)
    }

    suspend fun getFilteredProducts(token: String, filter: String): ProductResponse {
        return RetrofitInstance.api.getFilteredProducts(token, filter)
    }

    suspend fun getUserInfo(userName: String): UserInfoResponse {
        return RetrofitInstance.api.getUserInfo(userName)
    }

    suspend fun addProductToDatabase(token: String, product: AddProductRequest): AddProductResponse {
        return RetrofitInstance.api.addProduct(token, product.title, product.description, product.price_per_unit, product.units, product.is_active, product.amount_type,product.price_type)
    }

    suspend fun editProfile(token: String, userData: EditProfileRequest): EditProfileResponse {
        return RetrofitInstance.api.updateProfile(token, userData.email, userData.username)
    }

    suspend fun removeProduct(token: String, productId: String): RemoveProductResponse {
        return RetrofitInstance.api.removeProduct(token, productId)
    }
}