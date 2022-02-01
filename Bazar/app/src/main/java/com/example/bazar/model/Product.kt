package com.example.bazar.model
import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
data class Image(val _id: String, val image_id: String, val image_name: String, val image_path: String)

@Parcelize
@JsonClass(generateAdapter = true)
data class Product(val rating: Double,
                   val amount_type: String,
                   val price_type: String,
                   val product_id: String,
                   val username: String,
                   val is_active: Boolean,
                   val price_per_unit: String,
                   val units: String,
                   val description: String,
                   val title: String,
                   val images: List<String>,
                   val creation_time: Long
) : Parcelable

@JsonClass(generateAdapter = true)
data class ProductResponse(val item_count: Int, val products: List<Product>, val timestamp: Long)