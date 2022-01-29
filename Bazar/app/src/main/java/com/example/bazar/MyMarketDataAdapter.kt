package com.example.bazar

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bazar.model.Product
import com.example.bazar.model.UserInfo

class MyMarketDataAdapter (
    private var list: ArrayList<Product>,
    private val context: Context,
    private val listener: OnItemClickListener,
    private val listener2: OnItemLongClickListener
    ) :
    RecyclerView.Adapter<MyMarketDataAdapter.MyMarketDataViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    interface OnItemLongClickListener{
        fun onItemLongClick(position: Int)
    }

    // 1. user defined ViewHolder type - Embedded class!
    inner class MyMarketDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener {
        val prodImage : ImageView = itemView.findViewById(R.id.fragMMitemlayout_ProductImage)
        val prodName : TextView = itemView.findViewById(R.id.fragMMitemlayout_ProductName)
        val prodPrice : TextView = itemView.findViewById(R.id.fragMMitemlayout_ProductPrice)
        val ownerImage : ImageView = itemView.findViewById(R.id.fragMMitemlayout_OwnerImage)
        val ownerName : TextView = itemView.findViewById(R.id.fragMMitemlayout_OwnerName)
        val isActive : TextView = itemView.findViewById(R.id.fragMMitemlayout_active)

        init{
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(p0: View?) {
            val currentPosition = this.adapterPosition
            listener.onItemClick(currentPosition)

        }

        override fun onLongClick(p0: View?): Boolean {
            val currentPosition = this.adapterPosition
            listener2.onItemLongClick(currentPosition)
            return true
        }
    }

    // 2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMarketDataViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.my_market_item_layout, parent, false)
        return MyMarketDataViewHolder(itemView)
    }

    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: MyMarketDataViewHolder, position: Int) {
        val currentItem = list[position]

        holder.prodName.text = currentItem.title
        holder.prodPrice.text = currentItem.price_per_unit
        holder.ownerName.text = currentItem.username
        if( currentItem.is_active ){
            holder.isActive.text = "Actve"
        }
        else{
            holder.isActive.text = "Inactive"
        }

        val images = currentItem.images
        if( images != null && images.size > 0) {
            Log.d("xxx", "#num_images: ${images.size}")
        }
        Glide.with(this.context)
            .load(R.drawable.placeholder_image)
            .override(200, 200)
            .into(holder.prodImage);

        Glide.with(this.context)
            .load(R.drawable.placeholder_image)
            .override(80, 80)
            .into(holder.ownerImage);
    }

    override fun getItemCount() = list.size

    // Update the list
    fun setData(newlist: ArrayList<Product>){
        list = newlist
    }

}