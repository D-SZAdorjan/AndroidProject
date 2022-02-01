package com.example.bazar.model
import com.squareup.moshi.JsonClass

//import com.google.gson.annotations.SerializedName

data class User(var username: String="", var password: String="", var email: String="", var phone_number: String="")

@JsonClass(generateAdapter = true)
data class LoginRequest (
    var username: String,
    var password: String
)

@JsonClass(generateAdapter = true)
data class LoginResponse (
    var username: String,
    var email: String,
    var phone_number: Int,
    var token: String,
    var creation_time: Long,
    var refresh_time: Long
)

@JsonClass(generateAdapter = true)
data class UserInfo(
    var username: String,
    var phone_number: String,
    var email: String,
    var firebase_token: String,
    var is_activated: Boolean,
    var creation_time: Long
    )

@JsonClass(generateAdapter = true)
data class UserInfoResponse(val code: Int, val data : UserInfo, val time_stamp: Long)

@JsonClass(generateAdapter = true)
data class RegisterRequest(val username: String, val password: String, val email: String)

@JsonClass(generateAdapter = true)
data class RegisterResponse(val code: Int, val message : String, val timestamp: Long)

@JsonClass(generateAdapter = true)
data class ForgotPasswordRequest(val username: String, val email: String)

@JsonClass(generateAdapter = true)
data class ForgotPasswordResponse(val code: Int, val message : String, val timestamp: Long )

// GSon converter
//data class LoginRequest (
//    @SerializedName("username")
//    var username: String,
//
//    @SerializedName("password")
//    var password: String
//)
//
//
//data class LoginResponse (
//    @SerializedName("username")
//    var username: String,
//
//    @SerializedName("email")
//    var email: String,
//
//    @SerializedName("phone_number")
//    var phone_number: Int,
//
//    @SerializedName("token")
//    var token: String,
//
//    @SerializedName("creation_time")
//    var creation_time: Long,
//
//    @SerializedName("refresh_time")
//    var refresh_time: Long
//)