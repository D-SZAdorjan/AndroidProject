package com.example.bazar

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bazar.model.Product
import com.example.bazar.model.User
import com.example.bazar.model.UserInfo
import com.example.bazar.viewmodels.MainScreenViewModel
import org.w3c.dom.Text

class DataAdapter(
    private var list: ArrayList<Product>,
    private val context: Context,
    private val thisUser: UserInfo,
    private val listener: OnItemClickListener,
    private val listener2: OnItemLongClickListener
) :
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    interface OnItemLongClickListener{
        fun onItemLongClick(position: Int)
    }

    // 1. user defined ViewHolder type - Embedded class!
    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener {
        val prodImage : ImageView = itemView.findViewById(R.id.fragTL_ProductImage)
        val prodName : TextView = itemView.findViewById(R.id.fragTL_ProductName)
        val prodPrice : TextView = itemView.findViewById(R.id.fragTL_ProductPrice)
        val ownerImage : ImageView = itemView.findViewById(R.id.fragTL_OwnerImage)
        val ownerName : TextView = itemView.findViewById(R.id.fragTL_OwnerName)
        val orderBtn : Button = itemView.findViewById(R.id.fragTL_OrderNowBtn)

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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return DataViewHolder(itemView)
    }


    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]
        Log.d("xxx", currentItem.username + ": " + App.thisUser.username.compareTo( currentItem.username ).toString())
        if( App.thisUser.username.compareTo( currentItem.username ) == 0 ){
            holder.orderBtn.visibility = View.GONE
        }
        else{
            holder.orderBtn.visibility = View.VISIBLE
        }
        holder.prodName.text = currentItem.title
        holder.prodPrice.text = currentItem.price_per_unit
        holder.ownerName.text = currentItem.username
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