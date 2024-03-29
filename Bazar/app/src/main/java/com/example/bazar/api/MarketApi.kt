package com.example.bazar.api
import com.example.bazar.fragments.AddProductFragment
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

    @Multipart
    @POST(Constants.EDIT_PROFILE)
    suspend fun updateProfile(
        @Header("token") token: String,
        @Part("email") email: String,
        @Part("username") username: String,
    ) : EditProfileResponse

    /*@POST(Constants.ADD_PRODUCT)
    suspend fun addProduct(@Header("token") token: String, @Body product: AddProductRequest) : AddProductResponse*/
    @Multipart
    @POST(Constants.ADD_PRODUCT)
    suspend fun addProduct(
        @Header("token") token:String,
        @Part("title") title:String,
        @Part("description") description:String,
        @Part("price_per_unit") price_per_unit:Double,
        @Part("units") units:String,
        @Part("is_active") is_active:Boolean,
        @Part("amount_type") amount_type:String,
        @Part("price_type") price_type:String
    ): AddProductResponse

    @POST(Constants.REMOVE_PRODUCT)
    suspend fun removeProduct(@Header("token") token: String, @Query("product_id") product_id : String) : RemoveProductResponse

    /*@POST(Constants.UPDATE_PRODUCT)
    suspend fun updateProduct(@Header("token") token: String,
                                @Query("product_id") product_id: String,
                                @Body request: UpdateProductRequest) : UpdateProductResponse*/

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header("token") token: String): ProductResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getFilteredProducts(@Header("token") token: String,@Header("filter") filter: String): ProductResponse

    @GET(Constants.GET_USER_INFO)
    suspend fun getUserInfo(@Header("username") username: String): UserInfoResponse
}