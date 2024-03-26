package com.example.retrofitcallapidemo.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitcallapidemo.R
import com.example.retrofitcallapidemo.databinding.ViewItemImageBinding
import com.example.retrofitcallapidemo.model.Image

class ImageAdapter(private val context: Context, private val list: List<Image>) :
    RecyclerView.Adapter<ImageAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: ViewItemImageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ViewItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = list[position]
        Glide.with(context).load(currentItem.href).error(R.drawable.ic_launcher_foreground)
            .placeholder(R.drawable.ic_launcher_background).into(holder.binding.imgImage)
    }
}