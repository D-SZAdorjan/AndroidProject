package com.example.bazar.api
import com.example.bazar.model.*
import com.example.bazar.utils.Constants
import retrofit2.Response
import retrofit2.http.*

interface MarketApi {
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST(Constants.REGISTER_URL)
    suspend fun register(@Body request: RegisterRequest) : RegisterResponse

    @POST(Constants.FORGOTTENPASS_URL)
    suspend fun sendPasswordReset(@Body request: ForgotPasswordRequest) : ForgotPasswordResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header("token") token: String): ProductResponse

    @GET(Constants.GET_USER_INFO)
    suspend fun getUserInfo(@Header("username") username: String): UserInfoResponse
}