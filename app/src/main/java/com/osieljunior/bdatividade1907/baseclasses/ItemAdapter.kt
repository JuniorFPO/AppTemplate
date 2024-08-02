package com.osieljunior.bdatividade1907.baseclasses


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.osieljunior.bdatividade1907.R

class StoreAdapter(private val storeList: List<Item>) : RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store = storeList[position]
        holder.storeNameTextView.text = store.name
        holder.storeEmailTextView.text = store.email
        Glide.with(holder.storeImageView.context)
            .load(store.imageUrl)
            .into(holder.storeImageView)
    }

    override fun getItemCount(): Int {
        return storeList.size
    }

    class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val storeImageView: ImageView = itemView.findViewById(R.id.storeImageView)
        val storeNameTextView: TextView = itemView.findViewById(R.id.storeNameTextView)
        val storeEmailTextView: TextView = itemView.findViewById(R.id.storeEmailTextView)
    }
}
